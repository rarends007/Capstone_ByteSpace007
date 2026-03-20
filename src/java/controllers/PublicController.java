/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;


import business.bytespace.Super.User;
//import data.UserDB;
import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.apache.catalina.realm.SecretKeyCredentialHandler;

//import utilities.validation.NewUserValidation; No longer need with the increased modularization I implemented


//defined java classes
import utilities.Utility;

/**
 *
 * @author raren
 * This processes all public facing requests.
 */
public class PublicController extends HttpServlet {

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
        
            HttpSession session = request.getSession();
            ArrayList errors = new ArrayList();
            ArrayList messages = new ArrayList();

            String action = request.getParameter("action");
            String url = "/index.jsp";
            
            String username;
            String firstname;
            String middlename;
            String lastname;
            String password;
            String confirmPassword;
            //String credential; no longer needed with new modularization of registration functionality
            String role;
            
            switch (action) {
                case ("login"): 
                    //1. session will store the username and other non confidential fields
                    //2. security roles control the login and password and references the role from the security context
                    //    so now no need to manually retrieve the role from the database, the role config in web.xml handles that 
                    
                    //get credentials
                    errors.clear();
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    
                    if(session.getAttribute("username") == null){ //protects the current logged in user until they click logout
                        if(Utility.handleLogin(request, username, password, errors)){ //remember errors is reference type ArrayList() -> errors are passed by reference from called method to refernce object in memory "errors"

                            session.setAttribute("username", username);
                             System.out.println("PublicController -> User " + username + " has logged in.");
                        }else{
                             System.out.println("A actor attempted to login.");
                             url = "/public-authorization/login.jsp";
                        }
                    }else{
                        username = session.getAttribute("username").toString();
                        url = "/public-authorization/login.jsp";
                        errors.add(username + " is already logged in.");
                        username = "";
                    }
                    break;
                case ("logout"):
                    request.logout();
                    session.removeAttribute("username");
                    url = "/index.jsp";
                    break;
                case ("register"):
                    //instantiate a User
                    //get request att
                    //add them to the User
                    //pass user to insertUser() 
                    //User is an Obj reference, so User can have credential updated and assigned in the inserUser() func
                    
                    errors.clear();
                    
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    confirmPassword = request.getParameter("confPassword");
                    firstname = request.getParameter("firstname").trim();
                    middlename = request.getParameter("middlename");
                    lastname = request.getParameter("lastname");
                    role = request.getParameter("role");
                    
                    User user = new User();
                    user.setUsername(username);;
                    user.setFirstname(firstname);
                    user.setMiddlename(middlename);
                    user.setLastname(lastname);
                    user.setRole(role);
                    
                     Utility.handleRegistration(user,  password, confirmPassword,  errors, messages);

                    url = "/public-authorization/register.jsp";
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

