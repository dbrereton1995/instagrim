/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;

/**
 *
 * @author dbrer
 */
@WebServlet(name = "profile", urlPatterns = {"/profile", "/profile/*"})
public class profile extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Cluster cluster;
    boolean onlydoonce = false;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public profile() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args[] = Convertors.SplitRequestPath(request);
        switch (args.length) {
            // instagrim/profile
            case 2:
                showProfile( request, response);
                break;
            // instagrim/profile/<username>
            case 3:
                showProfile(args[2], request, response);
                break;
        }
    }

    private void showProfile(String username, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            RequestDispatcher rd = request.getRequestDispatcher("/userProfile.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("asdasd", username);
            rd.forward(request, response);
            onlydoonce = true;
        
    }

}
