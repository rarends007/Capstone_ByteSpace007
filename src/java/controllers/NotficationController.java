/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.bytespace.Message;
import business.bytespace.Notification;
import business.bytespace.Super.User;
import data.MessageDB;
import data.NotificationDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import data.NotificationDB;
import business.bytespace.Notification;
/**
 *
 * @author raren
 */
@WebServlet(name = "NotficationsController", urlPatterns = {"/NotficationsController"})
public class NotficationController extends HttpServlet {

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
        
        //Notification ops
        
        
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        String loggedInUserID = session.getAttribute("userID").toString();
        Integer loggedInUserIDInt = -1;
        try{
            loggedInUserIDInt = (int) Integer.valueOf(loggedInUserID);
        }catch(NumberFormatException ex) {
            System.err.println("Issue parsing the loggedInUserID -> \nNumberFormatException -> " + ex );
        }
        
        String url = "/Member"; //call member controller so that the profile data loads, should be able to pass URL params too.
        
        String action = request.getParameter("action");
        
        HashMap<Integer, Notification> AllNotificationsMap = NotificationDB.getAllNotificationsForUserByUserID(loggedInUserIDInt);
        try{
            session.setAttribute("notificationsMap", AllNotificationsMap);
            System.out.println("NotificationController -> Notifications map loaded.");
        }catch(Exception ex){
            System.err.println("Exception getting all notifications for user " + username + "NotificationController -> \n\txception: "  + ex);
        }

        switch (action) {
            case "set_noficiations_viewed":
                System.out.println("entered display_notificatoins switch case.");
                /*
                TODO:
                    insertNotificatoin fires if user messages another user - DONE
                    Later if we create a way for user to follow another user, then insert a notification to the followed user in that event. -DONE
                    GET
                    1. populate a collection of notifications when jsp notification icon clicked - DONE
                    2. pass that collection back to the jsp using a variable called notificationsMap type <Integer, Notfication> - DONE
                
                    * Now in this case display_notificatoins -> 
                     1. On click remove hide CSS, on click notifications button again, readd hide CSS to notification list div using JS - DONE
                     2. Anytime notification icon clicked twice, set all notifications is_viewed value to true for the user. 
                     3.  Use CSS to make it look different based on that value -> consult Oleksandr about what he 
                                wants to do for that CSS - May go a different direction for step 3
                 */
                boolean unviewedExist = false;
                for(Notification notification : AllNotificationsMap.values()){
                    if(notification.getIsViewed() == false){
                        unviewedExist = true;
                        break;
                    }
                }
                try{
                    NotificationDB.setNotificationViewedByUserID(loggedInUserIDInt);
                }catch(Exception ex){
                    System.out.println("set nofications seen to true -> "+ ex);
                }
                break;

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
