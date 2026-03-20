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
        <div>
            <nav class="floatNavRight" style="margin-top: 50px;"><a href="./../index.jsp">Home</a></nav>
        </div>
        
        <div class="member-login-form" style="margin-top: 25px;">
            <form action="./../Public" method="post">
                <input type="hidden" name="action" value="login"/>
                
                <div>
                    <label>Username</label>
                    <input type="text" name="username" id="username"/>
                </div>
                
                <div>
                    <label>Password</label>
                    <input type="text" name="password" id="password"/>
                </div>
                
                <div>
                    <input type="submit" value="Login"/>
                </div>

            </form>
            <span>${errors}</span>
        </div>
    </body>
</html>
