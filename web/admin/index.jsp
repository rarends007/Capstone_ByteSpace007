<%-- Document : index Created on : Mar 18, 2026, 5:26:58 AM Author : raren --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Admin Profile</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
            </head>

            <body>
                <c:import url="/member/left_panel.jsp" />
                <div class="main_content">
                    <div class="log_container">
                        <nav>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/Admin?action=getAllUsers">Manage
                                        Users</a>
                                </li>
                                <li><a href="${pageContext.request.contextPath}/Admin?action=getUserList">User List</a></li>
                                <li><a href="${pageContext.request.contextPath}/Message">Messages</a></li>
                            </ul>

                        </nav>
                    </div>
                </div>
                <c:import url="/member/right_panel.jsp" />
                <script src="${pageContext.request.contextPath}/member/script.js"></script>
            </body>

            </html>