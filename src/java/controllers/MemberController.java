/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.bytespace.Super.Post;
import business.bytespace.Super.User;
import data.BlockedDB;
import data.CommentDB;
import data.FollowersDB;
import data.ImageDB;
import data.PostDB;
import data.ProfileDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@MultipartConfig( //Needed so that the servlet can process mulitipart files.
        fileSizeThreshold = 1024 * 1024 * 1, //1MB 
        maxFileSize = 1024 * 1024 * 10, //10MB
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
        String username = session.getAttribute("username").toString();

        ArrayList errors = new ArrayList();
        ArrayList messages = new ArrayList();
        HashMap<Integer, Post> posts = new HashMap<Integer, Post>();
        String userStatus = "";
        HashMap SuggestedUsersHashMap = new HashMap();

        String action = request.getParameter("action");
        if (action == null) {
            action = "No action";
        }

        String url = "/member/index.jsp";

        String toOther = request.getParameter("to_other"); //Added s0 that the new comment will show up on post. for other profile functionality

        boolean pageControllerIsMember = request.getRequestURL().toString().contains("Member");//getting request url -> https://kodejava.org/how-do-i-get-servlet-request-url-information/
        int userID = UserDB.getUserID(username);

        SuggestedUsersHashMap = UserDB.getSuggestedUsers(userID);
        request.setAttribute("SuggestedUsersHashMap", SuggestedUsersHashMap);

        if (pageControllerIsMember) {
            String profilePhotoPathLoad = ProfileDB.getProfilePhotoPath(userID); //call db method to get the photo and later all profile info that is loaded will also be populated in this switch case as well

            if (profilePhotoPathLoad == null) {
                profilePhotoPathLoad = "";
            } else {
                session.setAttribute("profile_photo", profilePhotoPathLoad);
                System.out.println("photo path is: " + profilePhotoPathLoad);
            }

            try {
                LinkedHashMap<Integer, String> following = FollowersDB.getFollowing(userID);
                LinkedHashMap<Integer, String> followers = FollowersDB.getFollowers(userID);

                request.setAttribute("numFollowing", following.size());
                request.setAttribute("numFollowers", followers.size());
            } catch (SQLException ex) {
                errors.add("Unable to retrieve following numbers.");
            }

            try {
                posts = PostDB.getUserPosts(userID);
                userStatus = ProfileDB.getUserStatus(userID);
            } catch (SQLException ex) {
                Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
                errors.add("Unable to retrieve profile posts.");
            }

        }
        
        //Notifications init retreival 
        /**
         * GET
                    1. In the top get area declare a try/catch block to load notifications and set the JSP is_unseen_notification value to true if notifications are returned from 
                        getAllUnviewedNotificationsByUserID DB function
                    2. Pass the boolean value of isUnseenNotifications back to the nofication.jsp
         */
        try{
            
        }catch(Exception ex){
            System.err.println("Issue retreiving init notifications -> \n\tError: " + ex);
        }

        switch (action) { //post
            case "uploadProfilePhoto":

                //Part 1 add photo to file system in C:/bytespace/photos/username/file.jpg
                String profilePhotoFilePath = "";
                try {
                    //profilePhotoFilePath =  IO.uploadFile(request, response, messages, ""); //replace "" with the members username later retrieved from the session object
                    profilePhotoFilePath = IO.uploadFileV2(request, response, messages, username); //new version of function
                    System.out.println("The profile photo path adding to db is: " + profilePhotoFilePath);
                } catch (ServletException ex) {
                    System.out.println("Issue with Servlet file parts -> \nError thrown:" + ex);
                }

                //part 2, submit photo path string to the database in the profile table
                if (ProfileDB.updateProfilePhoto(userID, profilePhotoFilePath)) {
                    messages.add("Profile photo updated");
                } else {
                    errors.add("Unable to update profile photo, try again later.");
                }

                url = "/member/index.jsp";
                break;

            case "uploadImage":
                String image = "";
                try {
                    image = IO.uploadFileV2(request, response, messages, username);
                    System.out.println("The profile photo path adding to db is: " + image);
                } catch (ServletException ex) {
                    System.out.println("Issue with Servlet file parts -> \nError thrown:" + ex);
                }
                boolean success = false;
                success = PostDB.uploadImage(image, userID);
                if (success) {
                    action = "getImageForUser";
                }
                else{
                    break;
                }
            case "getImageForUser":
                ArrayList<String> photoFrilePaths = new ArrayList();
                try {
                    photoFrilePaths = ImageDB.getUserImagePhotoPathsById(userID);
                    System.out.print("Images retrieved");
                } catch (Exception ex) {
                    Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
                    errors.add("Unable to get images.");
                }
                url = "/member/gallery.jsp";
                request.setAttribute("gallery", photoFrilePaths);
                break;
            case "updateStatus":
                String inputtedStatus = request.getParameter("newStatus");

                try {
                    if (inputtedStatus != null) {
                        ProfileDB.setUserStatus(userID, inputtedStatus);

                        userStatus = ProfileDB.getUserStatus(userID);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
                    errors.add("Unable to update Status.");
                }

                break;
            case "get_all_users":
                System.out.println("Member -> case 'get_all_users' hit");
                HashMap allUsersHashMap = new HashMap();
                allUsersHashMap = UserDB.getAllUsers();
                System.out.println(allUsersHashMap);
                request.setAttribute("allUsersHashMap", allUsersHashMap);

                url = "/member/show_all_profiles.jsp";
                break;
            case "load_other_profile":
                System.out.println("Member -> case 'load_other_profile' hit");

                /*DONE: RA
                //create a new jsp to put the viewed user data into based on the other user profile selected
                //pull the other users profile data using DB functions. -> profile data needed -> 
                            //status, user info, profile_picture, posts, images associated to posts, 
                            //number following and followers(NOT the actual usernames of them for privacy)
                //add that pulled data to the request object passing to the jsp
                //ensure the request object variables are correct in the JSP
                //Test the shit out of it.
                 */
                try {
                    HashMap<Integer, Post> loadedProfilePosts = new HashMap<Integer, Post>();
                    String loadedProfileStatus = "";

                    //get other users ID coming in from the show_all_profiles.jsp page request object
                    int loadedProfileUserID = Integer.parseInt(request.getParameter("userID"));

                    User loadedUserFromProfileselected = UserDB.getUser(loadedProfileUserID);

                    if (loadedUserFromProfileselected != null) {
                        request.setAttribute("loadedProfileUsername", loadedUserFromProfileselected.getUsername());
                    }
                    request.setAttribute("loadedProfileUserID", loadedProfileUserID);
                    
                    try {
                    if (BlockedDB.isUserBlocked(userID, loadedProfileUserID)) {
                        url = "/Block?action=getBlockedUsers";
                        break;
                    }
                    } catch (Exception ex) {
                        Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //get the follower and "following" and "followers" values for  loaded profile
                    try {
                        LinkedHashMap<Integer, String> following = FollowersDB.getFollowing(loadedProfileUserID);
                        LinkedHashMap<Integer, String> followers = FollowersDB.getFollowers(loadedProfileUserID);

                        request.setAttribute("numFollowingOther", following.size());
                        request.setAttribute("numFollowersOther", followers.size());
                    } catch (SQLException ex) {
                        errors.add("Unable to retrieve following numbers.");
                    }

                    //get the posts and user status for the loaded profile
                    try {
                        posts = PostDB.getUserPosts(loadedProfileUserID);
                        loadedProfileStatus = ProfileDB.getUserStatus(loadedProfileUserID);

                        request.setAttribute("posts", posts);
                        request.setAttribute("loadedProfileStatus", loadedProfileStatus);
                    } catch (SQLException ex) {
                        Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
                        errors.add("Unable to retrieve profile posts.");
                    }

                    //Get the profile photo for the loaded profile
                    String profilePhotoPathLoad = ProfileDB.getProfilePhotoPath(loadedProfileUserID);

                    if (profilePhotoPathLoad == null) {
                        profilePhotoPathLoad = "";
                    } else {
                        request.setAttribute("loaded_profile_profile_photo", profilePhotoPathLoad);
                        System.out.println("photo path is: " + profilePhotoPathLoad);
                    }
                    url = "/member/otherProfile.jsp";
                } catch (NumberFormatException ex) {
                    System.out.println("Member -> load_other_profile -> \nNumberFormatException: " + ex);
                }

                break;
            case "makePost":
                String postText = request.getParameter("postText");
                String imageURL = "";
                try {
                    imageURL = IO.uploadFileV2(request, response, messages, username);
                    System.out.println("The profile photo path adding to db is: " + imageURL);
                } catch (ServletException ex) {
                    System.out.println("Issue with Servlet file parts -> \nError thrown:" + ex);
                }

                String test = username + "/upload/";
                int postId = -1;
                success = false;
                if (!test.equals(imageURL)) {
                    postId = PostDB.makePost(userID, imageURL, postText);
                } else {
                    postId = PostDB.makePost(userID, null, postText);
                    success = true;
                }
                if (postId != -1 && !test.equals(imageURL)) {
                    success = PostDB.createPostImage(imageURL, postId, userID);
                }
                if (success) {
                    try {
                        posts = PostDB.getUserPosts(userID);
                    } catch (Exception ex) {
                        Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
                        errors.add("Unable to Make Post.");
                    }

                }
                break;
            case "post_comment":

                String commentText = request.getParameter("comment_text");

                try {
                    int postID = Integer.parseInt(request.getParameter("post_id"));

                    CommentDB.insertComment(userID, postID, commentText);

                    if (toOther != null) {
                        url = "/member/otherProfile.jsp";
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("issue converting postID memberController case post_comment -> " + ex);
                } catch (NullPointerException ex) {
                    System.err.println("postID is NULL memberController case post_comment");
                }

                break;

            case "delete_comment":

                try {
                    int commentID = Integer.parseInt(request.getParameter("comment_id"));
                    CommentDB.deleteCommentByID(commentID);
                    messages.add("comment deleted");
                    System.out.println("comment id deleted is -> " + commentID);
                } catch (NumberFormatException ex) {
                    System.err.println("MemberController -> case delete_comment -> converting commentID to int -> \nNumberFormatExcetion " + ex);
                } catch (NullPointerException ex) {
                    System.err.println("MemberController -> case delete_comment -> converting commentID to int -> \nNullPointerException " + ex);
                } finally {
                    System.out.println("commentID successfully converted to int");
                }
                break;
            case "delete_post":
                System.out.print("MemberController -> delete_post logic hit\n");

                try {
                    int postID = Integer.parseInt(request.getParameter("post_id"));
                    System.out.println("postID to delete is: " + postID + "\n");
                    PostDB.deletePostByPostID(postID);
                    messages.add("Post Deleted");
                    try {
                        posts = PostDB.getUserPosts(userID);
                    } catch (SQLException ex) {
                        System.out.println("MemberController -> case delete_post -> getting posts after delete -> SQLException: " + ex);
                    }

                    request.setAttribute("posts", url);

                } catch (NumberFormatException ex) {
                    System.err.println("MemberController -> case delete_post -> converting commentID to int -> \nNumberFormatExcetion " + ex);
                } catch (NullPointerException ex) {
                    System.err.println("MemberController -> case delete_post -> converting commentID to int -> \nNullPointerException " + ex);
                } finally {
                    System.out.println("postID successfully converted to int");
                }
                break;
            case "display_notifications":
                System.out.println("entered display_notificatoins switch case.");
                /*
                TODO:
                    insertNotificatoin fires if user messages another user
                    Later if we create a way for user to follow another user, then insert a notification to the followed user in that event.
                    POST
                    1. populate a collection of notifications when jsp notification icon clicked
                    2. pass that collection back to the jsp using a variable called notificationsHashMap type <Integer, Notfication>
                    
                    
                 */
                break;

        }

        request.setAttribute("userID", userID);
        session.setAttribute("userStatus", userStatus);
        request.setAttribute("messages", messages);
        request.setAttribute("errors", errors);
        request.setAttribute("posts", posts);

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
