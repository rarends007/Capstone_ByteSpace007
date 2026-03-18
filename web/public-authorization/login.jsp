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
    </head>
    <body>
        <div>
            <nav><a href="./../index.jsp">Home</a></nav>
        </div>
        
        <div>
            <form action="./../Public" method="post">
                <input type="hidden" name="action" value="login"/>

                <label>Username</label>
                <input type="text" name="username" id="username"/>

                <label>Password</label>
                <input type="text" name="password" id="password"/>

                <input type="submit" value="Login"/>


            </form>
        </div>
    </body>
</html>
