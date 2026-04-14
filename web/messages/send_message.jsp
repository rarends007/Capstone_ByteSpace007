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
        <h4>Send Message</h4>
        <form action="${pageContext.request.contextPath}/Message" method="post">
            <input type="hidden" name="action" value="send_message"/>

            <select name="selected_recipient" id="selected_recipient" class="recipient_selector"> 
                <option value="">choose a recipient</option>
                <!--TODO: Take each recipient and load them into the messages_receiver db field in message table --> 
                <c:forEach var="item" items="${users}">
                    <option value="${item.key}">${item.value.getUsername()}</option>
                </c:forEach>
            </select>

            <div class="reply_message_container">    
                <textarea id="message_body" name="message_body" rows="20" cols="100" class="message_reply_body">
                </textarea>
            </div>
            <div>
                <input type="submit" value="Send" class="button_primary"/>
            </div>
        </form>
        <span>${messages}</span>
    </body>
</html>
