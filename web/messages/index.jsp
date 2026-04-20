<%-- Document : admin_and_member_messages Created on : Mar 24, 2026, 4:41:34 PM Author : raren --%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Messaging</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
                <link rel="stylesheet" type="text/css"
                    href="${pageContext.request.contextPath}/messages/messages.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
            </head>

            <body>
                <c:import url="/member/left_panel.jsp" />
                <div class="main_content">
                    <div class="messages_container">
                        <div class="user_list_container">
                            <ul class="user_list">
                                <c:forEach var="item" items="${chatUsers}">
                                    <c:if test="${item.key != userID}">
                                        <li id="user-${item.key}" class="chat_user">
                                            <div class="chat_avatar">
                                                ${item.value.getFirstname()},${item.value.getLastname()}</div>
                                            ${item.value.getUsername()}
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                            <button class="new_chat">+</button>
                        </div>
                        <c:import url="/messages/recieved_message.jsp" />
                    </div>
                    <div class="send_message_container hidden">
                        <c:import url="/messages/send_message.jsp" />
                    </div>
                    <span>${messages}</span>
                </div>
                <c:import url="/member/right_panel.jsp" />
            </body>
            <script src="${pageContext.request.contextPath}/member/script.js"></script>

            </html>