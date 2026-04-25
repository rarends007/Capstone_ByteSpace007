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

import business.bytespace.Message;
import data.MessageDB;
import data.NotificationDB;
import java.util.Map;
import javax.servlet.http.HttpSession;

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

        HashMap<Integer, User> users = UserDB.getAllUsers();
        if (users != null) {
            request.setAttribute("users", users);
        }
        try {
            int userID = Integer.parseInt(session.getAttribute("userID").toString());
            HashMap<Integer, Message> messagesForLoggedInUser = new HashMap();
            messagesForLoggedInUser = MessageDB.retrieveAllMessagesForUser(userID);

            // Creating HashMap with unique users to display in the chat
            HashMap<Integer, User> chatUsers = new HashMap();
            for (Map.Entry<Integer, User> entry : users.entrySet()) {
                for (Message message : messagesForLoggedInUser.values()) {
                    if (entry.getKey()== message.getSenderID() || entry.getKey()== message.getRecieverID()) {
                        chatUsers.put(entry.getKey(), entry.getValue());
                    }
                }
            }

            // Sorting messages by user
            HashMap<Integer, ArrayList<Message>> messagesByUser = new HashMap<>();

            for (Message message : messagesForLoggedInUser.values()) {
                int userId = -1;
                if(message.getRecieverID() == userID) {
                    userId = message.getSenderID();
                }
                else{
                    userId = message.getRecieverID();
                }
                
                boolean isExists = messagesByUser.containsKey(userId);

                if (!isExists) {
                    ArrayList<Message> array = new ArrayList<>();
                    array.add(messagesForLoggedInUser.get(message.getMessageID()));
                    messagesByUser.put(userId, array);
                } else {
                    ArrayList<Message> array = messagesByUser.get(userId);
                    array.add(messagesForLoggedInUser.get(message.getMessageID()));
                    messagesByUser.put(userId, array);
                }
            }

            request.setAttribute("messagesByUser", messagesByUser);
            request.setAttribute("chatUsers", chatUsers);
            request.setAttribute("messagesForLoggedInUser", messagesForLoggedInUser);
        } catch (NullPointerException ex) {
            System.err.println("Message DB case retrieve_messages -> Null -> \nNull Exception Error " + ex);
        } catch (Exception ex) {
            System.err.println("Message DB case retrieve_messages -> Null -> \nNull Exception Error " + ex);
        }

        String action = request.getParameter("action");
        
        //Ensures usernames are cached for the search functionality
        try {
            HashMap<Integer, User> userNameSearchMap = UserDB.getAllUsers();
            if (userNameSearchMap != null) {

            } else {
                userNameSearchMap = new HashMap<>();
            }
            request.setAttribute("userNameSearchMap", userNameSearchMap);
        } catch (Exception ex) {
            System.err.println("MemberController -> failed to populate users -> \n\tException " + ex);
        }
        
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
                        if(NotificationDB.insertNotificationForUserByUserID(recieverUserIDInt, "New message from " + session.getAttribute("username"))){
                            System.out.println("\nMessage notificatoin made to receiving user.");
                        }
                    } catch (Exception ex) {
                        System.err.println("Message Controller -> send_message -> Error sending message -> \nError Thrown: " + ex);
                    }
                    break;
                case "reply_message_load":
                    System.out.println("reply_message_load hit");
                    try {
                        int messageIDToReplyTo = Integer.parseInt(request.getParameter("message_id"));

                        Message message = MessageDB.getMessageByID(messageIDToReplyTo);
                        String doGoback = request.getParameter("go_back");

                        if (message != null) {
                            request.setAttribute("messageReplyingTo", message);
                            url = "/messages/index.jsp";
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("MessageController -> case send_message -> \nExcettion: " + ex);
                    } catch (Exception ex) {
                        System.err.println("MessageController -> case send_message -> \nExcettion: " + ex);
                    }
                    break;
                case "reply_message":
                    try {
                        String messageReplyText = request.getParameter("message_reply_body");
                        int senderID = Integer.parseInt(request.getParameter("sender_id"));
                        int recieverID = Integer.parseInt(request.getParameter("reciever_id"));

                        timestamp = LocalDateTime.now();

                        Message responseMessage = new Message(recieverID, senderID, messageReplyText, timestamp); //the reciever is the person replying to the message here, "you"

                        MessageDB.insertMessage(responseMessage);
                        
                        messages.add("Replied to message from " + UserDB.getUsername(senderID)); //the sender is the one that sent the message to us here
                        request.setAttribute("messages", messages);
                        url = "/messages/index.jsp";
                    } catch (Exception ex) {
                        System.err.println("MessageController -> case reply_message\nException: " + ex);
                    }

                    break;
                case "delete_message":
                    System.out.println("MessageController -> case delete_message logic executed");

                    try {
                        int message_id = Integer.parseInt(request.getParameter("message_id"));
                        MessageDB.deleteMessage(message_id);

                        //update messages displayed
                        int userID = Integer.parseInt(session.getAttribute("userID").toString());
                        HashMap<Integer, Message> messagesForLoggedInUser = new HashMap();
                        messagesForLoggedInUser = MessageDB.retrieveAllMessagesForUser(userID);

                        request.setAttribute("messagesForLoggedInUser", messagesForLoggedInUser);

                        //ensures the option for recieved messages stays selected on "received"
                        request.setAttribute("option", "received");

                    } catch (NumberFormatException ex) {
                        System.err.println("MessageDB -> case delete_message -> \nNumberFormatException: " + ex);
                    } catch (Exception ex) {
                        System.err.println("MessageDB -> case delete_message -> \nException: " + ex);
                    }

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
