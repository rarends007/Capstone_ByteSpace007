<%-- 
    Document   : test_functionalities_links
    Created on : Mar 20, 2026, 4:35:27 PM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test Page</h1>
         <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/index.jsp">Admin Portal</a></li>
                    <li><a href="${pageContext.request.contextPath}/member/index.jsp">Member Portal</a></li>
                    <li><a href="${pageContext.request.contextPath}/public-authorization/register.jsp">Register</a></li>
                    <li><a href="${pageContext.request.contextPath}/public-authorization/login.jsp">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/member/upload_member_profile_photo.jsp">upload photo</a></li>
                    <li><a href="${pageContext.request.contextPath}/member/messages/send_recieve_message_admin_and_member.jsp">Messaging</a>
                </ul>
            </nav>
    </body>
</html>
