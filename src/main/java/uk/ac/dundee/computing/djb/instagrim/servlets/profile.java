/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.djb.instagrim.servlets;

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
import uk.ac.dundee.computing.djb.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.djb.instagrim.lib.Convertors;
import uk.ac.dundee.computing.djb.instagrim.models.PicModel;
import uk.ac.dundee.computing.djb.instagrim.models.User;

import uk.ac.dundee.computing.djb.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.djb.instagrim.stores.Pic;

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
            // instagrim/profile/username
            case 2:
                //initProfile(request, response, args[2]);
                break;
            // instagrim/profile/<username>
            case 3:
                showCurrentProfile(args[2], request, response);
                break;
        }
    }

    private void initProfile(HttpServletRequest request, HttpServletResponse response, String username) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/profile/userprofile.jsp");
        HttpSession session = request.getSession();
        User user = new User();

        //if(user.checkUsernameExists(username)){
        session.setAttribute("username", username);
        // }
        rd.forward(request, response);
    }

    private void showCurrentProfile(String username, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/userProfile.jsp");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        // PicModel tm = new PicModel();
        //  tm.setCluster(cluster);
        // java.util.LinkedList<Pic> lsPics = tm.getPicsForUser(username);
        //  request.setAttribute("Pics", lsPics);
        rd.forward(request, response);

    }

}
