<%-- 
    Document   : register
    Created on : Mar 18, 2026, 6:38:35 AM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <h1>Registration</h1>
        
    <body>
        
        <div>
            <nav><a href="${pageContext.request.contextPath}/index.jsp">Home</a></nav>
        </div>
        
        <div>
            <form action="${pageContext.request.contextPath}/Public" method="post">
                <input type="hidden" name="action" value="register"/>
                
                <input type="hidden" name="role" value="MEMBER"/>

                <label>Username</label>
                <input type="text" name="username" id="username"/>

                <label>Firstname</label>
                <input type="text" name="firstname" id="firstname"/>

                <label>middlename</label>
                <input type="text" name="middlename" id="middlename"/>

                <label>Lastname</label>
                <input type="text" name="lastname" id="lastname"/>


                <label>Password</label>
                <input type="text" name="password" id="password"/>

                <label>Confirm Password</label>
                <input type="text" name="confPassword" id="confPassword"/>

                <input type="submit" value="Register"/>
                
            </form>
            <span>${errors}</span>
            <span>${messages}</span>
        </div>
       
    </body>
</html>
