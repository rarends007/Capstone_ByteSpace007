<%-- 
    Document   : recieve_message
    Created on : Mar 24, 2026, 5:03:19 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="chat_container" >
    <c:forEach var="entry" items="${messagesByUser}">           
        <div class="message_container"  id="chat-${entry.key}">
            <ul class="chat_messages">
                <c:forEach var="message" items="${entry.value}">
                    <c:choose>
                        <c:when test="${userID == message.senderID}">
                            <li class="my_message_container">
                                <div class="my_message">${message.messageText}</div>
                                <div class="message_actions">
                                    <div class="message_time">${message.timeStamp}</div>

                                    <form action="${pageContext.request.contextPath}/Message" method="post">
                                        <input type="hidden" name="action" value="delete_message"/>
                                        <input type="hidden" name="message_id" value="${message.messageID}"/>
                                        <input type="submit" value="Delete"/>
                                    </form>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="recieved_message_container">
                                <div class="recieved_message">${message.messageText}</div>
                                <div class="message_actions">
                                    <div class="message_time">${message.timeStamp}</div>

                                    <form action="${pageContext.request.contextPath}/Message" method="post">
                                        <input type="hidden" name="action" value="reply_message_load"/>
                                        <input type="hidden" name="message_id" value="${message.messageID}"/><!--TODO: use this to populate the recieved message send from the MessageController to the retreived messages page-->
                                        <input type="submit" value="Reply"/>
                                    </form>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
            </ul>
            <c:import url="/messages/reply_message.jsp"/>
        </div>

    </c:forEach>
</div>
