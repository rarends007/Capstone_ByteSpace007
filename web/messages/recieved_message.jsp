<%-- 
    Document   : recieve_message
    Created on : Mar 24, 2026, 5:03:19 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Received Messages</title>
    </head>
    <body>
        <h1>Received Messages</h1>

        <table>
               <thead>
                    <th>From</th>
                    <th>To</th>
                    <th>Message</th>
                    <th>Date</th>
                    <th></th>
                    <th></th>
                </thead>
                <tbody>
                
                <c:forEach var="message" items="${messagesForLoggedInUser}">
                    <tr>
                        <td>${message.value.getSenderOrReceiverUsername(message.value.getSenderID())}</td>
                        <td>${message.value.getSenderOrReceiverUsername(message.value.getRecieverID())}</td>
                        <td>${message.value.getMessageText()}</td>
                        <td>${message.value.getTimeStamp()}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/Message" method="post">
                                <input type="hidden" name="action" value="reply_message_load"/>
                                <input type="hidden" name="message_id" value="${message.value.getMessageID()}"/><!--TODO: use this to populate the recieved message send from the MessageController to the retreived messages page-->
                                <input type="submit" value="Reply"/>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/Message" method="post">
                                <input type="hidden" name="action" value="delete_message"/>
                                <input type="hidden" name="message_id" value="${message.value.getMessageID()}"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                    
              
                </c:forEach>

            </tbody>
        </table> 
        ${messages}
    </body>
</html>
