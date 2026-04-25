/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.bytespace.Super.User;
import data.FollowersDB;
import data.NotificationDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author raren
 */
//@WebServlet(name = "FriendsController", urlPatterns = {"/FriendsController"}) TODO need to add to deployment descriptor or it won't work, realms too -RA
public class FriendsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        String message = null;
        HashMap<Integer, String> follows = new HashMap<Integer, String>();

        String action = request.getParameter("action");
        if (action == null) {
            action = "No action";
        }

        String url = "/member/follow.jsp";

        int userID = UserDB.getUserID(username);
        
        HashMap<Integer, User> SuggestedUsersHashMap = new HashMap<>();
        SuggestedUsersHashMap = UserDB.getSuggestedUsers(userID);
        session.setAttribute("SuggestedUsersHashMap", SuggestedUsersHashMap);

        switch (action) {
            case "getFollowing" -> {

                try {
                    follows = FollowersDB.getFollowing(userID);
                    session.setAttribute("follows", follows);
                    session.setAttribute("numFollowing", follows.size());
                } catch (Exception ex) {
                    message = "Unable to retrieve following.";
                }

                if (follows.isEmpty()) {
                    request.setAttribute("noFollow", "You aren't following anyone!");
                }
                
                session.setAttribute("title", "Following");
                
                url = ""; //inf loop prevention
            }
            case "getFollowers" -> {

                try {
                    follows = FollowersDB.getFollowers(userID);
                    session.setAttribute("follows", follows);
                    session.setAttribute("numFollowers", follows.size());
                } catch (Exception ex) {
                    message = "Unable to retrieve followers.";
                }

                if (follows.isEmpty()) {
                    request.setAttribute("noFollow", "You have no followers!");
                }

                session.setAttribute("title", "Followers");
                
                 url = ""; //inf loop prevention
            }
            case "removeFollow" -> {
                int followingID = Integer.parseInt(request.getParameter("followingID"));
                String title = request.getParameter("title").trim();
                
                try {
                    if (session.getAttribute("title").equals("Followers")) {
                        url="/Friends?action=get" + title;
                        FollowersDB.removeFollow(followingID, userID);
                        message = "Successfully removed follower";
                        
                       
                    } else {
                        url="/Friends?action=get" + title;
                        FollowersDB.removeFollow(userID, followingID);
                        message = "Removed from your following list";
                    }
                } catch (Exception ex) {
                    message = "Unable to remove user from list.";
                }
            }
            case "followUser" -> {
                int followingID = Integer.parseInt(request.getParameter("followingID"));

                //This ensures that when a user follows another user that the user will be notified they followed them.
                if (NotificationDB.insertNotificationForUserByUserID(followingID, username + " started following you.")) {
                    System.out.println("Notification successfully sent to the followed user.");
                }
                //End notification

                try {
                    FollowersDB.addFollow(userID, followingID);
                    System.out.println("Successfully followed user");
                } catch (Exception ex) {
                    System.out.println("Unable follow user");
                }
                
                String suggestedClicked = request.getParameter("suggestedClicked");
                
                if (suggestedClicked != null){
                    url = "/Member?action=follow_suggested_member_clicked";
                }
            }
        }
        request.setAttribute("message", message);
        
        if(url.isBlank()){
            url = "/member/follow.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
