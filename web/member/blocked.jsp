<%-- Document : blocked Created on : Apr 15, 2026, 4:15:34 PM Author : se757706 --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Blocked Users</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
            </head>

            <body>
                <c:import url="left_panel.jsp" />
                <div class="main_content">
                    <div class="blocked_container">
                        <h3>Blocked Users</h3>
                        <p>${noneBlocked}</p>
                        <ul class="follow_list">
                            <c:forEach items="${blockedUsers}" var="blocked">
                                <li>${blocked.value}
                                    <form action="${pageContext.request.contextPath}/Block" method="post">
                                        <input type="hidden" name="action" value="unblockUser">
                                        <input type="hidden" name="blockedUserID" value="${blocked.key}">
                                        <input type="submit" value="Unblock" class="button_primary">
                                    </form>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="blocked_action_bar">
                            <a href="Member?action=get_all_users" class="button_secondary">All Users</a>
                            <a href="Block?action=getBlockedUsers" class="button_primary">Reload list</a>
                        </div>
                        <p>${message}</p>
                        <p class="error_message">${error}</p>
                    </div>
                </div>
                <c:import url="right_panel.jsp" />
                <script src="${pageContext.request.contextPath}/member/script.js"></script>
            </body>

            </html>