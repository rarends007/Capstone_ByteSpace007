<%-- 
    Document   : login
    Created on : Mar 18, 2026, 6:38:20 AM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
    </head>

    <body>
        <div class="gradient_bg">
            <h1>ByteSpace</h1>
        </div>
        <div class="login_container">
            <div class="login_links">
                <span><a href="${pageContext.request.contextPath}/public-authorization/register.jsp">Registration</a></span> /
                <span> Login</span>
            </div>
            <h3>Welcome Back</h3>
            <form action="Public" method="post" class="login-form_container">
                <input type="hidden" name="action" value="login">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" required>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>
                <p class="error_message">${errors}</p>
                <input type="submit" value="Log In" class="login_btn">
            </form>
        </div>
    </body>
</html>
