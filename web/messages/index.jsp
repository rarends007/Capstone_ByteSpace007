<%-- 
    Document   : admin_and_member_messages
    Created on : Mar 24, 2026, 4:41:34 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messaging</title>        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/messages.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
    </head>
    <body>
        <c:import url="/member/left_panel.jsp" />
        <div class="main_content">
            <div class="messages_container">
                <ul>
                    <c:forEach var="item" items="${chatUsers}">                
                        <li id="user-${item.key}" class="chat_user"> ${item.value.getUsername()}</li>
                        </c:forEach>
                </ul>
            </div>
        <!--            <form id="form" action="${pageContext.request.contextPath}/Message" method="get" name="message_form">
                        <select name="messaging_option" id="messaging_option" > value of select gets passed to controller as a parameter.
                            <option value="">--Choose and Option--</option>
                            <option value="send">Send Message</option>
                            <option value="received">Received Message(s)</option>
                        </select>
                    </form>-->
            <c:import url="/messages/recieved_message.jsp"/>
            <c:import url="/messages/send_message.jsp"/>
            <span>${messages}</span>
        </div>
        <div class="right_panel"></div>
    </body>       
    <script src="${pageContext.request.contextPath}/member/script.js"></script>

</html>
