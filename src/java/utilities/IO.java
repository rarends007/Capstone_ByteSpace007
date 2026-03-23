/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.Part;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raren
 */
public class IO {
    
    /**
     * This method is to be called only from a servlet and uploads a file to the file path specified. Which can then be retrieved from again calling request.getContextPath()
     * For this project the file path is -> C:\apache-tomcat-9.0.115-windows-x64\work\Catalina\localhost\bytespace
     * 
     * All photo filename strings will need to also be added and deleted from the database as needed
     * @param request
     * @param response
     * @param message
     * @param username
     * @throws ServletException 
     * @return String  filepath to be stored in the db and then called in html Image tags
     */
    public static String uploadFile(HttpServletRequest request, HttpServletResponse response, ArrayList<String> message, String username)
                    throws ServletException{
        
        String fileName = null;
        String path = "C:/bytespace/photos/";
        //String path = "/images/"; Ok screw it, I need to develop a new method.... ugh
        //String writePath = "\\.\\..\\..\\C\\bytespace\\photos\\" + username + "\\"; //causes it to go relative from the working dir, what pain in the ass
        try{
            
                //https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
                // Source - https://stackoverflow.com/a/3634906
                //create a directory if it doesn't exist using java
                File theDir = new File(path);
                if (!theDir.exists()){
                    theDir.mkdirs();
                }

                //1. know -> lots of parts are coming in from the servlet
                 Part filePart = request.getPart("file"); //2. this gets the part coming from the servlet called file
                 fileName = filePart.getSubmittedFileName(); //3. this cretes the filename from that file part -> you can add prefixes and sufffixes as needed here
                 for (Part part: request.getParts()){ //4. take the file name of the upload and create a file in x folder
                     //part.write(request.getContextPath() + ".\\..\\" + fileName );
                     part.write(path + fileName );
                 }
                 message.add("The file was uploaded sucessfully.");
        }catch(IOException ex){
                 if(fileName.isBlank()){
                     message.add("No photo selected.");
                 }else{
                     message.add("There was an issue uploading the file.");
                 }
                 
                 System.out.println("IO.Java -> uploadFile() -> issue with parsing file.\n Error message: " + ex);
        }
        System.out.println("\nFile path is: " + path + " filename is: " + fileName + "\n\n");
        return path + fileName;
    }
    
    public static String uploadFileV2(HttpServletRequest request, HttpServletResponse response, ArrayList<String> message, String username) 
                    throws ServletException{
        
        String fileName = null;
        String UPLOAD_DIRECTORY = "upload";
        String uploadPath = null;
        try{
            
                //https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
                // Source - https://stackoverflow.com/a/3634906
                //create a directory if it doesn't exist using java
                
                uploadPath = request.getServletContext().getRealPath("")//https://www.codejava.net/java-ee/servlet/apache-commons-fileupload-example-with-servlet-and-jsp
                 + username + File.separator + UPLOAD_DIRECTORY;
                File theDir = new File(uploadPath);
                if (!theDir.exists()){
                    theDir.mkdirs();
                }

                //1. know -> lots of parts are coming in from the servlet
                 Part filePart = request.getPart("file"); //2. this gets the part coming from the servlet called file
                 fileName = filePart.getSubmittedFileName(); //3. this cretes the filename from that file part -> you can add prefixes and sufffixes as needed here
                 for (Part part: request.getParts()){ //4. take the file name of the upload and create a file in x folder
                     //part.write(request.getContextPath() + ".\\..\\" + fileName );
                     part.write(uploadPath + File.separator +  fileName ); //File.separator is just the separator char so \\ or / for windows OS
                 }
                 message.add("The file was uploaded sucessfully.");
        }catch(IOException ex){
                 if(fileName.isBlank()){
                     message.add("No photo selected.");
                 }else{
                     message.add("There was an issue uploading the file.");
                 }
                 
                 System.out.println("IO.Java -> uploadFile() -> issue with parsing file.\n Error message: " + ex);
        }
        System.out.println("\nFile path is: " + uploadPath + File.separator + " filename is: " + fileName + "\n\n");
        //return uploadPath + File.separator + fileName; absolute/ full paths don't work with the image element tag so gotta return a relative path.
        return  username + "/" + UPLOAD_DIRECTORY + "/" +  fileName; //img tag will not accept a ' / ' as a prefix to the url string... ugh so much work just for this little thing
    }
    
    public static void deleteFile(HttpServletRequest request, HttpServletResponse response, ArrayList<String> message)
                    throws ServletException{
        
        //TODO: find out how and then write logic to delete a file from the server path
        
        //Note: in db method in PhotoDB need to also delete photo path from the db
        
    }
    
    
}
