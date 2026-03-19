<%-- 
    Document   : admin_level_add_delete_users
    Created on : Mar 18, 2026, 10:03:34 PM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <div>
            <h1>Add User</h1>
            <form action="./../Admin" method="post">
                <input type="hidden" name="action" value="addUser"/>

                
            </form>
        </div>
        
        <div>
            <h1>Delete User</h1>
            <c:forEach var="item" items="${usersHashMap}">
                <tr>
                    <td><a href="Private?userid=${item.value.getUserID()}&username=${item.value.getUsername()}&action=deleteUser"> <c:out value="${item.value.getUsername()}" /><c:out value="item.value.getFirstname()"/>||<c:out value="item.value.getLastname()"/></a></td> <!--<href="parameters passed to controller from url in get request">Part shown to user</a>-->
                </tr>
            </c:forEach>
       
        </div>
    </body>
</html>
