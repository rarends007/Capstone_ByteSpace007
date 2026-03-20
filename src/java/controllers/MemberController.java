/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

//self made classes
import utilities.IO;

/**
 *
 * @author raren
 */
//@WebServlet(name = "MemberController", urlPatterns = {"/MemberController"})

@MultipartConfig (   //Needed so that the servlet can process mulitipart files.
      fileSizeThreshold = 1024 * 1024 * 1, //1MB 
      maxFileSize = 1024 * 1024 *10, //10MB
      maxRequestSize = 1024 * 1024 * 100 //100GB
)
public class MemberController extends HttpServlet {

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
            
            switch (action){
                case "uploadProfilePhoto": 
                    try{
                       IO.uploadFile(request, response, messages);
                    }catch(ServletException ex){
                        System.out.println("Issue with Servlet file parts -> \nError thrown:" + ex);
                    }
                    url = "/member/upload_member_profile_photo.jsp";
                    break;
            }
            
            request.setAttribute("messages", messages);
            request.setAttribute("errors", errors);
       
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
