<%-- 
    Document   : send_message
    Created on : Mar 24, 2026, 5:03:01 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Message</title>
    </head>
    <body>
        <h1><strong>Reply to Message From: ${messageReplyingTo.getSenderOrReceiverUsername(messageReplyingTo.getSenderID())}</strong></h1>
        <form action="${pageContext.request.contextPath}/Message" method="post">
            <input type="hidden" name="action" value="reply_message"/>
            <!--<input type="hidden" name="messageIDReplyingTo" value="${messageReplyingTo.getMessageID()}"/>-->
            <input type="hidden" name="reciever_id" value="${messageReplyingTo.getRecieverID()}"/>
            <input type="hidden" name="sender_id" value  ="${messageReplyingTo.getSenderID()}"/>
            <div>    
            
                <p>${messageReplyingTo.getMessageText()}</p>
                <textarea name="message_reply_body" rows="20" cols="100">insert message</textarea>
            </div>
            <div>
                <input type="submit" value="Reply"/>
            </div>
        </form>
        <span>${messages}</span>
    </body>
</html>
