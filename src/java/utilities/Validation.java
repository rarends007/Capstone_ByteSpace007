/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.ArrayList;

/**
 *
 * @author raren
 */
public class Validation {
    
    /**
     * 
     * @param fieldName name of field
     * @param str value passed for analysis
     * @param message ArrayList<String> -> Will add appropriate error message if blank or null to reference obj passed
     * @return  boolean -> T if notNullOrBlank | F otherwise
     */
    public static boolean notNullorBlank(String fieldName, String str, ArrayList<String> message){
        boolean notNullOrBlank = true;
        if (str == null || str.equals("")){
            notNullOrBlank = false;
            message.add(str + " must not be blank");
        }
        
        return notNullOrBlank;
    }
    
    public static boolean notGreaterThanXChars(String fieldName, String str, ArrayList<String> message){
        //TODO: write up this validation
        return false;
    }
}
