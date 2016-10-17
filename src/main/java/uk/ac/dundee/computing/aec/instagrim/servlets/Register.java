/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

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
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/Register", "/Register.*"})
public class Register extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }


@Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException{
         RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String confirmpassword=request.getParameter("confirmpassword");
        String email=request.getParameter("email");
        String first_name=request.getParameter("first_name");
        String last_name=request.getParameter("last_name");
        String address1=request.getParameter("address1");
        String city=request.getParameter("city");
        String country=request.getParameter("country");
        String postcode=request.getParameter("postcode");
        
        
        User us=new User();
        us.setCluster(cluster);
        boolean ExistingUsername = us.checkUsernameExists(username); 
       // boolean NullPassword = us.IsPasswordNull(password);
        int StrongPassword = us.IsPasswordStrong(password);
        boolean PasswordsMatch = us.doPasswordsMatch(password, confirmpassword);
        boolean ExistingEmail = us.checkEmailExists(email);
        
        
        
         if(PasswordsMatch){request.setAttribute("registerError", "Passwords don't match!");}
       else  if(ExistingUsername){request.setAttribute("registerError", "Username already exists!");}
       else if(ExistingEmail){request.setAttribute("registerError", "Email already exists!");}
        else{
       
       
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);
        
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


