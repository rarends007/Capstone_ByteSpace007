/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.bytespace.Super;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author raren
 */
public class User implements Serializable{
    private Integer userID;
    private String username;
    private String firstname;
    private String middlename;
    private String lastname;
    private String role; //only used in registration
    private String credential; //only used in registration

    
    public User(){
        userID = null;
        username = "";
        firstname = "";
        middlename = "";
        lastname = "";
        role = null;
        credential = null;
    }

    public User(Integer userID, String username, String firstname, String middlename, String lastname, String credential) {
        this.userID = userID;
        this.username = username;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.credential = credential;
    }
    
    //for admin user control
    public User(String username, String firstname, String middlename, String lastname, String credential, String role) {
        this.username = username;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.role = role;
        this.credential = credential;
    }
    
    
    
    
    
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
    
    
    
    
            
}
