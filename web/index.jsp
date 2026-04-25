<%-- 
    Document   : index
    Created on : Mar 18, 2026, 6:58:32 AM
    Author     : raren
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
    </head>
    <body>
        <div>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Admin">Admin Portal</a></li>
                    <li><a href="${pageContext.request.contextPath}/Member">Member Portal</a></li>
                    <li><a href="${pageContext.request.contextPath}/public-authorization/register.jsp">Register</a></li>
                    <li><a href="${pageContext.request.contextPath}/public-authorization/login.jsp">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/test_functionalities_links.jsp">test page</a></li>
                </ul>
            </nav>
            <div>
                <span>${username}</span>
            </div>
            <div>
                <form action="Public" method="post">
                    <input type="hidden" name="action" value="logout"/>
                    
                    <input type="submit" value="Logout"/>
                </form><!--Will communicate to PublicController to log based on whichever action was taken from the switch in that controller  -->
            </div>
        </div>
        <div>TODO write content</div>
    </body>
</html>
