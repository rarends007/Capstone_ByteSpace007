<%-- Document : show_all_profiles Created on : Apr 10, 2026, 1:42:18 AM Author : raren --%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>All Profiles</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
            </head>

            <body>
                <c:import url="left_panel.jsp" />
                <div class="main_content">
                    <div class="all_users_list_container">
                        <h2>All Profiles</h2>
                        <ul class="user_list all_users">
                            <c:forEach var="users" items="${allUsersHashMap}">
                                <li>
                                    <div class="suggestion_content">
                                        <div class="user_avatar">
                                            ${users.value.getFirstname()},${users.value.getLastname()}</div>
                                        <a
                                            href="${pageContext.request.contextPath}/Member?action=load_other_profile&userID=${users.value.getUserID()}">${users.value.getUsername()}</a>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <c:import url="right_panel.jsp" />
                <script src="${pageContext.request.contextPath}/member/script.js"></script>
            </body>

            </html>