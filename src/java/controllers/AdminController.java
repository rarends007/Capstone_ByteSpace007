/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.bytespace.Log;
import business.bytespace.Super.User;
import data.LogDB;
import utilities.Utility;

import data.UserDB;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raren
 */
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

        HashMap<Integer, User> userHashMap = null; //always check if null before using this
        try {
            userHashMap = UserDB.getAllUsers();
        } catch (Exception ex) {
            System.out.println("Admin loading userHashMap error -> " + ex);
        }

        String url = "/admin/index.jsp";

        String action = request.getParameter("action");

        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> errors = new ArrayList<>();

        //URL parameters
        String userID;
        String username;
        String firstname;
        String middlename;
        String lastname;
        String password;
        String confirmPassword;
        String role;

        if (action != null) {
            switch (action) {
                case "getAllUsers":
                    if (userHashMap != null) {
                        System.out.println("Admin ->switch case getAllUsers -> AllUsersHashMap populated successfully.");
                    } else {
                        System.out.println("Admin ->switch case getAllUsers -> AllUsersHashMap is null");
                    }
                    request.setAttribute("usersHashMap", userHashMap);
                    url = "/admin/admin_level_add_delete_users.jsp";

                    //Start userDeletedMessage Logic
                    //ALL of this is ONLY for displaying a success message on the page when an admin deletes a user 
                    String adminIsDeletingUser = request.getParameter("adminIsDeletingUser");
                    if (adminIsDeletingUser != null && adminIsDeletingUser.equals("true")) {
                        String userDeletedMessage = request.getParameter("userDeletedMessage");

                        request.setAttribute("userDeletedMessage", userDeletedMessage);
                    }
                    //End userDeletedMessage Logic

                    break;
                case "deleteUser":
                    messages.clear();
                    userID = request.getParameter("userID");
                    username = request.getParameter("username");

                    if (UserDB.deleteUser(Integer.parseInt(userID))) {
                        System.out.println("user deleted " + username);
                        messages.add("username " + username + "has been successfully deleted from the database.");

                        url = "/Admin?action=getAllUsers&userDeletedMessage=" + messages.get(0) + "&adminIsDeletingUser=true"; //so the list will repopulate with the missing user shown as gone, proper refresh, NOTE: only one ? allowed in URL to properly parametize it on forward
                        System.out.println("Admin -> switch case 'deleteUser' url is -> " + url);                                                                        //values get called in Admin ->  getAllUsers switch case, where it is directed
                    } else {
                        errors.add("Unable to delete user " + username + ", issue with the database connection.");
                        url = "/admin/admin_level_add_delete_users.jsp";
                    }

                    break;

                case "addUser":
                    username = request.getParameter("username");
                    firstname = request.getParameter("firstname");
                    middlename = request.getParameter("middlename");
                    lastname = request.getParameter("lastname");
                    password = request.getParameter("password");
                    confirmPassword = request.getParameter("confPassword");
                    role = request.getParameter("user_role_select");

                    User user = new User(username, firstname, middlename, lastname, password, role);

                    Utility.handleRegistration(user, password, confirmPassword, errors, messages);

                    if (userHashMap != null) {
                        request.setAttribute("usersHashMap", userHashMap);
                    }

                    url = "/admin/admin_level_add_delete_users.jsp";

                    break;
                case "getUserList":
                    HashMap<Integer, Log> loginLog = LogDB.getAllLoginLogs();

                    request.setAttribute("usersHashMap", userHashMap);
                    request.setAttribute("loginMap", loginLog);

                    url = "/admin/user_login_log.jsp";
                    break;
            }
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
