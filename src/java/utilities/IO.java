/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.Part;

/**
 *
 * @author raren
 */
public class IO {
    
    /**
     * This method is to be called only from a servlet and uploads a file to the file path specified. Which can then be retrieved from again calling request.getContextPath()
     * For this project the file path is -> C:\apache-tomcat-9.0.115-windows-x64\work\Catalina\localhost\bytespace
     * @param request
     * @param response
     * @param message
     * @throws ServletException 
     */
    public static void uploadFile(HttpServletRequest request, HttpServletResponse response, ArrayList<String> message)
                    throws ServletException{
        
        try{
                //1. know -> lots of parts are coming in from the servlet
                 Part filePart = request.getPart("file"); //2. this gets the part coming from the servlet called file
                 String fileName = filePart.getSubmittedFileName(); //3. this cretes the filename from that file part -> you can add prefixes and sufffixes as needed here
                 for (Part part: request.getParts()){ //4. take the file name of the upload and create a file in x folder
                     part.write(request.getContextPath() + ".\\..\\" + fileName );
                 }
                 message.add("The file was uploaded sucessfully.");
        }catch(IOException ex){
                 message.add("There was an issue uploading the file.");
                 System.out.println("IO.Java -> uploadFile() -> issue with parsing file.\n Error message: " + ex);
        }
    }
    
    public static void deleteFile(HttpServletRequest request, HttpServletResponse response, ArrayList<String> message)
                    throws ServletException{
        
        //TODO: find out how and then write logic to delete a file from the server path
        
    }
    
    
}
