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
    </head>
    <body>
        <div>
            <nav>
                <ul>
                    <li><a href="/bytespace/admin/index.jsp">Admin Portal</a></li>
                    <li><a href="/bytespace/member/index.jsp">Member Portal</a></li>
                    <li><a href="/bytespace/public-authorization/register.jsp">Register</a></li>
                    <li><a href="/bytespace/public-authorization/login.jsp">Login</a></li>
                </ul>
            </nav>
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
