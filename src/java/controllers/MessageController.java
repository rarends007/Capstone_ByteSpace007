/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.bytespace.Super.User;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.bytespace.Message;
import data.MessageDB;

/**
 *
 * @author raren
 */
public class MessageController extends HttpServlet {

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

        String url = "/messages/index.jsp";
        ArrayList<String> messages = new ArrayList();

        HttpSession session = request.getSession();

        //this controls the option sent back to the jsp when the js submits the form on the select element value in messages/index.js is set to value  'send' or 'received'
        String option = request.getParameter("messaging_option");
        if (option != null) {
            request.setAttribute("option", option);

            if (option.equals("send")) {
                //ensures the list on send_message.jsp is populated with user choices
                HashMap<Integer, User> users = UserDB.getAllUsers();
                if (users != null) {
                    request.setAttribute("users", users);
                }
            } else if (option.equals("received")) {
                try {
                    int userID = Integer.parseInt(session.getAttribute("userID").toString());
                    HashMap<Integer, Message> messagesForLoggedInUser = new HashMap();
                    messagesForLoggedInUser = MessageDB.retrieveAllMessagesForUser(userID);

                    request.setAttribute("messagesForLoggedInUser", messagesForLoggedInUser);
                    
                    
                } catch (NullPointerException ex) {
                    System.err.println("Message DB case retrieve_messages -> Null -> \nNull Exception Error " + ex);
                } catch (Exception ex) {
                    System.err.println("Message DB case retrieve_messages -> Null -> \nNull Exception Error " + ex);
                }
            }

        }

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "send_message":
                    System.out.println("send_message hit");

                    //values to pass to the message insert
                    LocalDateTime timestamp = LocalDateTime.now();

                    String recieverUserID = request.getParameter("selected_recipient");
                    String senderUserID = session.getAttribute("userID").toString();
                    String message_text = request.getParameter("message_body");
                    try {
                        int recieverUserIDInt = Integer.parseInt(recieverUserID);
                        int senderUserIDInt = Integer.parseInt(senderUserID);

                        Message sendingMessage = new Message(senderUserIDInt, recieverUserIDInt, message_text, timestamp);

                        MessageDB.insertMessage(sendingMessage);
                        System.out.println("Message successfully sent");
                    } catch (Exception ex) {
                        System.err.println("Message Controller -> send_message -> Error sending message -> \nError Thrown: " + ex);
                    }
                    break;
                case "reply_message_load":
                    System.out.println("reply_message_load hit");
                    try{
                          int messageIDToReplyTo  = Integer.parseInt(request.getParameter("message_id"));
                          
                          Message message = MessageDB.getMessageByID(messageIDToReplyTo);
                          
                          if (message != null){
                              request.setAttribute("messageReplyingTo", message);
                              url = "/messages/reply_message.jsp";
                          }
                    }catch (NumberFormatException ex){
                        System.err.println("MessageController -> case send_message -> \nExcettion: " + ex);
                    }catch (Exception ex){
                           System.err.println("MessageController -> case send_message -> \nExcettion: " + ex);
                    }
                    break;
                case "reply_message":
                    try{
                        String messageReplyText = request.getParameter("message_reply_body");
                        int senderID = Integer.parseInt(request.getParameter("sender_id"));
                        int recieverID = Integer.parseInt(request.getParameter("reciever_id"));
                        
                        timestamp = LocalDateTime.now();
                        
                        Message message = new Message(recieverID, senderID, messageReplyText, timestamp ); //the reciever is the person replying to the message here, "you"
                        
                        MessageDB.insertMessage(message);
                        messages.add("Replied to message from " + UserDB.getUsername(senderID));
                        
                        request.setAttribute("messages", messages);
                        url = "/messages/reply_message.jsp";
                    }catch(Exception ex){
                        System.err.println("MessageController -> case reply_message\nException: " + ex);
                    }
                    
                    
                    break;
                case "delete_message":
                    
                    break;

            }
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
