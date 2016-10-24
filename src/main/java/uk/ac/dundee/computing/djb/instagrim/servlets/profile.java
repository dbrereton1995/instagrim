package uk.ac.dundee.computing.djb.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.djb.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.djb.instagrim.lib.Convertors;
import uk.ac.dundee.computing.djb.instagrim.models.PicModel;
import uk.ac.dundee.computing.djb.instagrim.models.User;
import uk.ac.dundee.computing.djb.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.djb.instagrim.stores.Pic;

/**
 *
 * @author Daniel Brereton
 * @version 1.0
 * @since 23-10-2016
 */
@WebServlet(name = "profile", urlPatterns = 
    {
    "/profile",
    "/profile/*", 
    "/profile/editProfile"
    })
public class profile extends HttpServlet {

    private Cluster cluster;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public profile() {
        super();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        //grab entered values from corresponding inputs on the edit profile form
        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String country = request.getParameter("country");

        User user = new User();
        user.setCluster(cluster);
        //Returns LoggedIn object from current session
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        String username = lg.getUsername();

        RequestDispatcher rd = request.getRequestDispatcher("editUserDetails.jsp");
        //Does email already exist? If yes, boolean is true
        boolean ExistingEmail = user.checkEmailExists(email);
        if (ExistingEmail) {
            request.setAttribute("errorMsg", "Email already exists!");
            rd.forward(request, response);
        } else {
            //if the user has passed the above validation checks, Allow the user's edits to update their profile
            String[] userInfo = user.getUserInfo(username);
            session.setAttribute("firstname", userInfo[0]);
            session.setAttribute("lastname", userInfo[1]);
            session.setAttribute("country", userInfo[2]);
            session.setAttribute("email", userInfo[3]);

            //assign user object the entered data in the userprofiles table
            user.updateUserInfo(email, first_name, last_name, country, username);
            response.sendRedirect("/Instagrim/profile");
        }

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

        //Split URL into array, each element is split by '/'
        String args[] = Convertors.SplitRequestPath(request);

        //switch statement using length of the array as it's parameter
        switch (args.length) {
            //URL = instagrim/profile/
            case 2:
                showCurrentProfile(lg.getUsername(), request, response);
                break;
            //URL = instagrim/profile/<username>
            case 3:
                if(args[2].equals("editProfile")){
                    RequestDispatcher rd = request.getRequestDispatcher("/editUserDetails.jsp");
                    rd.forward(request, response);
                }else if(args[2].equals("deleteProfile")){
                    RequestDispatcher rd = request.getRequestDispatcher("/deleteProfile.jsp");
                    rd.forward(request, response);             
                }else{
                    //display corresponding profile to the <username> in the URL
                showCurrentProfile(args[2], request, response);
                break;
                }
            
        }
    }

    /**
     * Displays the profile page of the user currently logged in Extra features
     * of this page are editable first name, last name and profile picture
     *
     * @param username
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void showCurrentProfile(String username, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //initialise pictures uploaded by this specific user
        PicModel tm = new PicModel();
        tm.setCluster(cluster);
        Pic pic = tm.getProfilePic(username);
        java.util.LinkedList<Pic> lsPics = tm.getPicsForUser(username);
        
        HttpSession session = request.getSession();
        
        //create LoggedIn object to check if user is logged in
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");

        //create User object and assign it to the active cluster
        User user = new User();
        user.setCluster(cluster);

        //Split URL into array, each element is split by '/'
        String args[] = Convertors.SplitRequestPath(request);
        RequestDispatcher rd;

        //if Instagrim/profile/username is equal to the current user's username...
        if (args.length == 2 || args[2].equals(lg.getUsername())) {
            //display their username
            username = lg.getUsername();
            session.setAttribute("username", username);
            //display profile page with edit options
            rd = request.getRequestDispatcher("/userProfile.jsp");
        } else {
            //display this profile's username
            username = args[2];
            session.setAttribute("username", username);
            //display profile page with no edit options
            rd = request.getRequestDispatcher("/otherProfile.jsp");
        }

        //Set attributes of User Information, their pics and their profile picture
        String[] userInfo = user.getUserInfo(username);
        session.setAttribute("firstname", userInfo[0]);
        session.setAttribute("lastname", userInfo[1]);
        session.setAttribute("country", userInfo[2]);
        session.setAttribute("email", userInfo[3]);
        request.setAttribute("Pics", lsPics);
        request.setAttribute("profilePicture", pic);

        rd.forward(request, response);
    }

}
