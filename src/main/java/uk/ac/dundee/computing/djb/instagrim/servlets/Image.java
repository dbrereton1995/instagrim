package uk.ac.dundee.computing.djb.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import uk.ac.dundee.computing.djb.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.djb.instagrim.lib.Convertors;
import uk.ac.dundee.computing.djb.instagrim.models.PicModel;
import uk.ac.dundee.computing.djb.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.djb.instagrim.stores.Pic;

/**
 * Servlet implementation class Image
 */
@WebServlet(urlPatterns = {
    "/Image",
    "/Image/*",
    "/Thumb/*",
    "/Images",
    "/Images/*",
    "/Upload/*",
    "/Upload"

})
@MultipartConfig

public class Image extends HttpServlet {

    private Cluster cluster;
    private HashMap CommandsMap = new HashMap();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
        CommandsMap.put("Image", 1);
        CommandsMap.put("Images", 2);
        CommandsMap.put("Thumb", 3);
        CommandsMap.put("Upload", 4);

    }

    public void init(ServletConfig config) throws ServletException {

        cluster = CassandraHosts.getCluster();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // takes url splits it up per slash
        String args[] = Convertors.SplitRequestPath(request);
        int command;
        try {
            command = (Integer) CommandsMap.get(args[1]);
        } catch (Exception et) {
            error("Bad Operator", response);
            return;
        }
        switch (command) {
            case 1:
                DisplayImage(Convertors.DISPLAY_PROCESSED, args[2], response);
                break;
            case 2:
                DisplayImageList(args[2], request, response);
                break;
            case 3:
                DisplayImage(Convertors.DISPLAY_THUMB, args[2], response);
                break;
            case 4:             
                //get input from upload page
                RequestDispatcher rd = request.getRequestDispatcher("/upload.jsp");
                //set attribute "url" to the value of the split URL path
                request.setAttribute("url", args);
                rd.forward(request, response);

            default:
                error("Bad Operator", response);
        }
    }

    /**
     * Displays a list of the user's images
     * 
     * @param User
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void DisplayImageList(String User, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        java.util.LinkedList<Pic> lsPics = tm.getPicsForUser(User);
        RequestDispatcher rd = request.getRequestDispatcher("/UsersPics.jsp");
        request.setAttribute("Pics", lsPics);
        rd.forward(request, response);

    }

    /**
     * Displays an individual image
     * 
     * @param type
     * @param Image
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void DisplayImage(int type, String Image, HttpServletResponse response) throws ServletException, IOException {
       
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        Pic p = tm.getPic(type, java.util.UUID.fromString(Image));
        OutputStream out = response.getOutputStream();
        response.setContentType(p.getType());
        response.setContentLength(p.getLength());
        //out.write(Image);
        InputStream is = new ByteArrayInputStream(p.getBytes());
        BufferedInputStream input = new BufferedInputStream(is);
        byte[] buffer = new byte[8192];
        for (int length = 0; (length = input.read(buffer)) > 0;) {
            out.write(buffer, 0, length);
        }
        out.close();
    }

/**
     * Handles the HTTP <code>POST</code> method.
     * Mainly uploading pictures and profile pictures
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Grab the value of the profilepicture checkbox (which is a required field)
        String profilePicture = request.getParameter("profilepicture");
        //Display value of checkbox, should be 'on' when uploading a profile picture
        System.out.println("ProfilePic Checkbox value = " + profilePicture);
        
        for (Part part : request.getParts()) {
            System.out.println("Part Name " + part.getName());
            //Split URL into array, each element is split by '/'
            String args[] = Convertors.SplitRequestPath(request);
            String type = part.getContentType();
            String filename = part.getSubmittedFileName();
            String description = request.getParameter("description");
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            HttpSession session = request.getSession();
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            String username = "majed";
            if (lg.getlogedin()) {
                username = lg.getUsername();
            }
            if (i > 0) {
                byte[] b = new byte[i + 1];
                is.read(b);
                System.out.println("Length : " + b.length);
                PicModel tm = new PicModel();
                tm.setCluster(cluster);
                UUID profilePic = tm.insertPic(b, type, filename, username, description);
                //IF this picture is a profile picture...
                if(profilePicture.equals("on")){
                        //set it as the profile picture of the corresponding user
                        tm.setProfilePic(username, profilePic);
                }
                is.close();
            }
            RequestDispatcher rd = request.getRequestDispatcher("/upload.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * 
     * @param mess
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void error(String mess, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have a na error in your input</h1>");
        out.println("<h2>" + mess + "</h2>");
        out.close();
        return;
    }
}
