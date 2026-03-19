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
        
        <nav><a href="./../index.jsp">Home</a></nav>
        <div>
            
            <h1>Add User</h1>
            <form action="./../Admin" method="post">
                <input type="hidden" name="action" value="addUser"/>
                      <div>

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
                
                <label>Role:</label>

                <select name="user_role_select" id="user_role_select">
                  <option value="ADMIN">Administrator</option>
                  <option value="MEMBER">Member</option>
                </select>

                <input type="submit" value="Register"/>
                
            </form>
            <span>${errors}</span>
        </div>
                
            </form>
        </div>
        
        <div>
            <h1>Delete User</h1>
            <span>${errors}</span><!<!-- TODO: need to make this look nicer, functionality first though. - RA -->
            <span>${messages}</span>
            <span>${userDeletedMessage}</span>
            <br>
            <c:forEach var="item" items="${usersHashMap}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/Admin?userID=${item.value.getUserID()}&username=${item.value.getUsername()}&action=deleteUser"> <c:out value="${item.value.getUsername()}" /> | Remove User | </a></td> <!--<href="parameters passed to controller from url in get request">Part shown to user</a>-->
                </tr>
                <br>
            </c:forEach>
       
        </div>
    </body>
</html>
