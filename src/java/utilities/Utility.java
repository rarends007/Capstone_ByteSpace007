/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author raren
 */

import business.bytespace.Super.User;
import data.LogDB;
import data.ProfileDB;
import data.UserDB;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.realm.SecretKeyCredentialHandler;
import utilities.validation.NewUserValidation;


import org.apache.catalina.CredentialHandler; //https://tomcat.apache.org/tomcat-8.5-doc/config/credentialhandler.html

public class Utility {
    
    /**
     * 
     * @param request
     * @param username string
     * @param password string
     * @param errors array list
     * @return if login successful true, else false *Boolean*
     */
    public static boolean handleLogin(HttpServletRequest request, String username, String password, ArrayList errors){
       boolean isSuccessfulLogin = false;
        
        try{
            request.login(username, password);
            isSuccessfulLogin = true;
        }catch(ServletException ex){
            System.out.println("\nerror logging in: exception caught ->  Utillity -> handleLogin() -> "  + ex +  "\n" );
            errors.add("The username or password is incorrect or doesn't exist.");
        }
        
        return isSuccessfulLogin;
    }
    
    /**
     * handles the hard part of registering a user, to be called right after getting parameters for the user form the request and instaniting the user object with them
     * @param user
     * @param password
     * @param confirmPassword
     * @param errors
     * @param messages 
     */
    public static void handleRegistration(User user, String password, String confirmPassword, ArrayList<String> errors, ArrayList<String> messages){
        if(NewUserValidation.userValid(user, password, confirmPassword, errors)) {
                    SecretKeyCredentialHandler sc = null;
                    String credential = null;
                    
                    user.setUsername(user.getUsername().trim());
                    user.setFirstname(user.getFirstname().trim());
                    user.setMiddlename(user.getMiddlename().trim());
                    user.setLastname(user.getLastname().trim());
                    user.setRole(user.getRole().trim());
                    
                    try{
                            sc = new SecretKeyCredentialHandler();
                            sc.setAlgorithm("PBKDF2WithHmacSHA512");
                            sc.setKeyLength(256);
                            sc.setSaltLength(16);
                            sc.setIterations(4096);
                            
                            credential = sc.mutate(password);
                            user.setCredential(credential);
                            
                            if(UserDB.insertUser(user, errors)){
                                System.out.println("User " + user.getUsername() + " has been added as a " + user.getRole());
                                messages.add("User " + user.getUsername() + " has been added as a " + user.getRole());
                                
                                user.setUserID(UserDB.getUserID(user.getUsername()));
                                //Now create the users profile in the db
                                if(ProfileDB.createUserProfile(user)){
                                    System.out.println("The users profile has been created! ");
                                }else{
                                    System.out.println("Utiilty -> handleregistoion() -> Failure creating the users profile.");
                                }
                            }
                            
                     }catch(NoSuchAlgorithmException ex){
                         System.out.println("Exeception hasing password -> " + ex);
                     }
             }
      }                 
}
