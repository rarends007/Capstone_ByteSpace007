/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.bytespace.Super.User;
import data.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raren
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

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
        
        HashMap<Integer, User> userHashMap = UserDB.getAllUsers();
        
        String url = "/admin/index.jsp";
        
        String action = request.getParameter("action");
        
        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> errors = new ArrayList<>();
        
        switch (action){
            case "getAllUsers":
                if(userHashMap != null){
                    System.out.println("Admin ->switch case getAllUsers -> AllUsersHashMap populated successfully.");
                }else{
                    System.out.println("Admin ->switch case getAllUsers -> AllUsersHashMap is null");
                    HashMap<Integer, String> error = new HashMap<>();
                }
                request.setAttribute("usersHashMap", userHashMap);
                url = "/admin/admin_level_add_delete_users.jsp";
                
                
                //Start userDeletedMessage Logic
                //ALL of this is ONLY for displaying a success message on the page when an admin deletes a user 
                String adminIsDeletingUser = request.getParameter("adminIsDeletingUser");
                if(adminIsDeletingUser != null && adminIsDeletingUser.equals("true")){
                    String userDeletedMessage = request.getParameter("userDeletedMessage");
                    
                    request.setAttribute("userDeletedMessage", userDeletedMessage);
                }
                //End userDeletedMessage Logic
                
                
                break;
            case "deleteUser":
                messages.clear();
                String userID = request.getParameter("userID");
                String username = request.getParameter("username");
                
                if(UserDB.deleteUser(Integer.parseInt(userID))){
                    System.out.println("user deleted " + username);
                    messages.add("username " + username + "has been successfully deleted from the database.");
                    
                    url = "/Admin?action=getAllUsers&userDeletedMessage=" + messages.get(0) + "&adminIsDeletingUser=true"; //so the list will repopulate with the missing user shown as gone, proper refresh, NOTE: only one ? allowed in URL to properly parametize it on forward
                    System.out.println("Admin -> switch case 'deleteUser' url is -> " + url );                                                                        //values get called in Admin ->  getAllUsers switch case, where it is directed
                }else{
                    errors.add("Unable to delete user " + username + ", issue with the database connection.");
                    url = "/admin/admin_level_add_delete_users.jsp";
                }

                break;
        }
        
         request.setAttribute("errors", errors);
         request.setAttribute("messages", messages);
          
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
