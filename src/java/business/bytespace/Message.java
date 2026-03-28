/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.bytespace;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author raren
 */
public class Message implements Serializable{
   private Integer messageID;
   private String messageText;
   private Integer senderID;
   private  Integer recieverID;
   private LocalDateTime timeStamp;
       
    public Message(){
        messageID = null;
        messageText = null;
        senderID = null;
        recieverID = null;
        timeStamp = null;
    }
    
    public Message(Integer messageID, String messageText, Integer senderID, Integer recieverID, LocalDateTime timeStamp) {
        this.messageID = messageID;
        this.messageText = messageText;
        this.senderID = senderID;
        this.recieverID = recieverID;
        this.timeStamp = timeStamp;
    }
    
    public Message(Integer senderID, Integer recieverID, String messageText, LocalDateTime timeStamp) {
        this.messageID = messageID;
        this.messageText = messageText;
        this.senderID = senderID;
        this.recieverID = recieverID;
        this.timeStamp = timeStamp;
    }

    public Integer getMessageID() {
        return messageID;
    }

    public void setMessageID(Integer messageID) {
        this.messageID = messageID;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public Integer getRecieverID() {
        return recieverID;
    }

    public void setRecieverID(Integer recieverID) {
        this.recieverID = recieverID;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
    

}


