/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author raren
 */

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
}
