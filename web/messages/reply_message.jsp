<%-- 
    Document   : send_message
    Created on : Mar 24, 2026, 5:03:01 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form action="${pageContext.request.contextPath}/Message" method="post" name="reply_message">
    <input type="hidden" name="action" value="reply_message"/>
    <!--<input type="hidden" name="messageIDReplyingTo" value="${messageReplyingTo.getMessageID()}"/>-->
    <input type="hidden" name="reciever_id" value="${messageReplyingTo.getRecieverID()}"/>
    <input type="hidden" name="sender_id" value  ="${messageReplyingTo.getSenderID()}"/>
    <div class="reply_message_container">    
        <p>${messageReplyingTo.getMessageText()}</p>
        <textarea name="message_reply_body" rows="20" cols="100">insert message</textarea>
    </div>
    <div>
        <input type="submit" value="Reply" class="button_primary"/>
    </div>
</form>

