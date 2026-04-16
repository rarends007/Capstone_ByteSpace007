/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import data.BlockedDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author se757706
 */
public class BlockController extends HttpServlet {

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
        String error = null;
        HashMap<Integer, String> blocked = new HashMap<Integer, String>();

        String action = request.getParameter("action");
        if (action == null) {
            action = "No action";
        }

        String url = "/member/blocked.jsp";

        boolean pageControllerIsMember = request.getRequestURL().toString().contains("Member");//getting request url -> https://kodejava.org/how-do-i-get-servlet-request-url-information/
        int userID = UserDB.getUserID(username);

        switch (action) {
            case ("getBlockedUsers"):
                try {
                    blocked = BlockedDB.getBlockedUsers(userID);
                    session.setAttribute("blockedUsers", blocked);
                } catch (Exception ex) {
                    error = "Error retrieving blocked users";
                }
                break;
            case ("blockUser"):
                int blockedUserID = Integer.parseInt(request.getParameter("blockedUserID"));
                boolean success = false;

                try {
                    BlockedDB.blockUser(userID, blockedUserID);
                    message = "User blocked successfully";
                } catch (Exception ex) {
                    error = "Unable to block user";
                }
                break;
            case ("unblockUser"):
                blockedUserID = Integer.parseInt(request.getParameter("blockedUserID"));
                try {
                    BlockedDB.unblockUser(userID, blockedUserID);
                    message = "User unblocked successfully";
                } catch (Exception ex) {
                    error = "Unable to unblock user";
                }
                break;
        }
        request.setAttribute("error", error);
        request.setAttribute("message", message);

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
