package uk.ac.dundee.computing.djb.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.djb.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.djb.instagrim.models.User;

/**
 *
 * @author Daniel Brereton
 * @version 1.0
 * @since 23-10-2016
 */
@WebServlet(urlPatterns = {"/Register", "/Register.*"})
public class Register extends HttpServlet {

    Cluster cluster = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);
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
        
        //grab entered values from corresponding inputs on the registration form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");
        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String address1 = request.getParameter("address1");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String postcode = request.getParameter("postcode");

        User us = new User();
        us.setCluster(cluster);
        
        //Does username already exist? If yes, boolean is true
        boolean ExistingUsername = us.checkUsernameExists(username);
        //Do the passwords match? If yes, boolean is true
        boolean PasswordsMatch = us.doPasswordsMatch(password, confirmpassword);
        //Does email already exist? If yes, boolean is true
        boolean ExistingEmail = us.checkEmailExists(email);
        
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");

        //check that the username doesn't already exist and then...
        if (ExistingUsername) {
            request.setAttribute("errorMsg", "Username already exists!");
            rd.forward(request, response);
        }
        //... check that both the password and the password confirmation values match and then...
        if (PasswordsMatch == false) {
            request.setAttribute("errorMsg", "Passwords don't match!");
            rd.forward(request, response);
        }
        //...check that the email doesn't already exist
        if (ExistingEmail) {
            request.setAttribute("errorMsg", "Email already exists!");
            rd.forward(request, response);
        //if the user has passed the above validation checks, Register the User in the database (userprofiles table)
        } else {
            //assign user object the entered data in the userprofiles table
            us.RegisterUser(username, password, email, first_name, last_name, address1, city, country, postcode);
            response.sendRedirect("/Instagrim");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
