<%-- 
    Document   : recieve_message
    Created on : Mar 24, 2026, 5:03:19 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Received Messages</title>
    </head>
    <body>
        <div>
            <c:forEach var="entry" items="${messagesByUser}">           
                <div class="chat_container" id="${entry.key}">
                    <ul>
                    <c:forEach var="message" items="${entry.value}">
                        <li>
                    <c:choose>
                        <c:when test="${userID == message.senderID}">
                            <p class="my_message">${message.messageText}</p>
                        </c:when>
                        <c:otherwise>
                            <p class="recieved_message">${message.messageText}</p>

                        </c:otherwise>
                    </c:choose>
                    <p>${message.timeStamp}</p>

                    <form action="${pageContext.request.contextPath}/Message" method="post">
                        <input type="hidden" name="action" value="reply_message_load"/>
                        <input type="hidden" name="message_id" value="${message.messageID}"/><!--TODO: use this to populate the recieved message send from the MessageController to the retreived messages page-->
                        <input type="submit" value="Reply"/>
                    </form>
                    <form action="${pageContext.request.contextPath}/Message" method="post">
                        <input type="hidden" name="action" value="delete_message"/>
                        <input type="hidden" name="message_id" value="${message.messageID}"/>
                        <input type="submit" value="Delete"/>
                    </form>
                        </c:forEach>
                    </li>
                  </ul>
                </div>

            </c:forEach>
        </div>
        ${messages}
    </body>
</html>
