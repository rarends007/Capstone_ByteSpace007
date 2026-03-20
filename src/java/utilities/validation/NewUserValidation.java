/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities.validation;

import java.util.ArrayList;
import java.util.regex.Pattern;

import business.bytespace.Super.User;

/**
 *
 * @author raren
 */
public class NewUserValidation {
    
    /**
     *  This is intended to validate a User user, it checks for for null or blank && field
     * length proper for db && password length correct 
     * && password and conf pass are equal && password follows business rules
     * @param user User
     * @param password String
     * @param confirmPassword String
     * @param message 
     * @return boolean, true if valid
     */
    public static boolean userValid(User user, String password, String confirmPassword, ArrayList<String> message){
        boolean userValid = false;
        
        String username = user.getUsername();
        String firstname = user.getFirstname();
        String middlename = user.getMiddlename();
        String lastname = user.getLastname();
        String role = user.getRole();

        //Check for null or blank && field length proper for db && password length correct 
        // && password and conf pass are equal && password follows business rules
        if(isValidAllUserFieldsLoader(username, firstname, middlename, lastname, 
                password, confirmPassword, role, message)){
            userValid = true;
        }
        
        return userValid;
    }
    
    /**
     * The goal of this method is to ensure the user entered all values correctly on registering another user.
     * @param username
     * @param firstname
     * @param middlename
     * @param lastname
     * @param password
     * @param confirmPassword
     * @param role
     * @param message
     * @return true if user fields are correct
     */
    public static boolean isValidAllUserFieldsLoader(String username, String firstname, String middlename,
            String lastname, String password, String confirmPassword, String role, ArrayList<String> message){
        boolean allFieldsValid = false;
        
        boolean usernameNotBlank = false;
        boolean firstnameNotBlank = false;
        boolean middlenameNotBlank = false;
        boolean lastnameNotBlank = false;
        boolean passwordNotBlank = false;
        boolean confirmPasswordNotBlank  = false;
        boolean roleNotBlank = false;
        
        boolean usernameCorrectLen = false;
        boolean firstnameCorrectLen = false;
        boolean lastnameCorrectLen = false;
        boolean middlenameCorrectLen = false;
        boolean passwordCorrectLen = false;
        
        boolean passwordsMatch = false;
        boolean passwordFollowsRules = false;

        //blanks and nulls
        if(notNullorBlank("username", username, message)){usernameNotBlank = true;}
        if(notNullorBlank("firstname", firstname, message)){firstnameNotBlank = true;}
        if(notNullorBlank("middlename", middlename, message)){middlenameNotBlank = true;}
        if(notNullorBlank("lastname", lastname, message)){lastnameNotBlank = true;}
        if(notNullorBlank("password", password, message)){passwordNotBlank = true;}
        if(notNullorBlank("confirm password", confirmPassword, message)){confirmPasswordNotBlank = true;}
        if(notNullorBlank("role", role, message)){roleNotBlank = true;}

        //char length
        if(notGreaterThanXChars("username", username, 30, message )){usernameCorrectLen =true;}
        if(notGreaterThanXChars("firstname", firstname, 25, message)){firstnameCorrectLen = true;}
        if(notGreaterThanXChars("middlename", middlename, 25, message)){middlenameCorrectLen = true;}
        if(notGreaterThanXChars("lastname", lastname, 25, message)){lastnameCorrectLen = true;}
        
        //password validation
        if(notLessThanXChars("password", password, 8, message)){passwordCorrectLen = true;}
        if(passwordsMatch(password, confirmPassword, message)){passwordsMatch = true;}
        if(passwordFollowsRules(password, message)){passwordFollowsRules = true;}
        
        if(
                usernameNotBlank && firstnameNotBlank && middlenameNotBlank && lastnameNotBlank && passwordNotBlank && confirmPasswordNotBlank && roleNotBlank
                && usernameCorrectLen && firstnameCorrectLen && middlenameCorrectLen && lastnameCorrectLen && passwordCorrectLen
                && passwordsMatch && passwordFollowsRules
                ){
            allFieldsValid = true;
        }
        
        return allFieldsValid;
    }
    
    /**
     * 
     * @param fieldName name of field, used for inputting the proper field name into the error message
     * @param str value passed for analysis
     * @param message ArrayList<String> -> Will add appropriate error message if blank or null to reference obj passed
     * @return  boolean -> T if notNullOrBlank | F otherwise
     */
    public static boolean notNullorBlank(String fieldName, String str, ArrayList<String> message){
        boolean notNullOrBlank = true;
        if (str == null || str.equals("") || str.isBlank() || str.isEmpty()){
            notNullOrBlank = false;
            message.add(fieldName +  " must not be blank");
        }
        
        return notNullOrBlank;
    }
    
    /**
     * 
     * @param fieldName
     * @param str
     * @param maxAllowedLength
     * @param message
     * @return true if not greater than x chars
     */
    public static boolean notGreaterThanXChars(String fieldName, String str, int maxAllowedLength, ArrayList<String> message){
        
        boolean isCorrectLength = false;
        
        if(str.length() <= maxAllowedLength ){
            isCorrectLength = true;
        }else{
            message.add("The field " + fieldName + " must be no more than "  + maxAllowedLength + " characters long.");
        }
        return isCorrectLength;
    }
    
    /**
     * 
     * @param fieldName
     * @param str
     * @param minAllowedLength
     * @param message
     * @return true if not less than x chars
     */
    public static boolean notLessThanXChars(String fieldName, String str, int minAllowedLength, ArrayList<String> message){
        
        boolean isCorrectLength = false;
        
        if(str.length() < minAllowedLength ){
            message.add("The field " + fieldName + " must be no less than "  + minAllowedLength + " characters long.");
        }else{
            isCorrectLength = true;
        }
        
        return isCorrectLength;
    }
    
    
    /**
     * 
     * @param password1
     * @param password2
     * @param message
     * @return true if passwords are equal
     */
    public static boolean passwordsMatch(String password1, String password2, ArrayList<String> message){
        
        boolean passwordsMatch = false;
        
        if(password1.equals(password2)){
            passwordsMatch = true;
        }else{
            message.add("The password and confirmation password must be the same.");
        }
        
        return passwordsMatch;
    }
    
    /**
     * 
     * @param password
     * @param message
     * @return true if passwords follow business rules
     */
    public static boolean passwordFollowsRules(String password, ArrayList<String> message){ //
        
        String passwordRegex = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{1,})\\S$"; //https://regex101.com/r/fX8dY0/1
        
        boolean passwordGood = false;
        
        if(Pattern.matches(passwordRegex, password) && checkIfSpecialCharPresent(password, message) ){ //https://www.geeksforgeeks.org/java/regular-expressions-in-java/
            passwordGood = true;
        }else{
            message.add("Password must contain a uppercase and lowercase letter");
            message.add("Password must be at least 8 characters in length.");
            message.add("Password must contain a number.");
        }
        
        return passwordGood;
    }
    
    /**
     * Checks if the string contains a special character.Intended use is password verification, but it can work for other use cases as well.
     * @param str
     * @param message
     * @return bool indicating if a special character is present in the string, true if special char present, false if not
     */
    public static boolean checkIfSpecialCharPresent(String str, ArrayList<String> message){
        String[] specialChars =
            {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "]", "{", "}", "\\", "|", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?", "~", "`"};
        
        boolean specialCharPresent = false;
        
        for (int i = 0; i < specialChars.length; i++){
            if(str.contains(specialChars[i])){
                specialCharPresent = true;
                break;
            }
        }
        
        if(specialCharPresent == false){ message.add("The password must contain a special character."); }

        return specialCharPresent;
    }
}
