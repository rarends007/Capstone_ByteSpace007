<%-- 
    Document   : admin_and_member_messages
    Created on : Mar 24, 2026, 4:41:34 PM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messaging</title>
    </head>
    <body>
        <select id="messaging_option">
            <option value="">--Choose and Option--</option>
            <option value="send">Send Message</option>
            <option value="recieve">Recieve Message</option>
        </select>
    </body>
    
    <script src="${pageContext.request.contextPath}/member/messages/messaging.js"></script>
    
</html>
