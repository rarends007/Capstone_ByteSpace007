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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/admin.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
    </head>
    <body class="pageColor"> 
        <nav  class="floatNavLeft" style="margin-top: 100px; width: 200px;">
            <ul>
                <li>
                     <a href="${pageContext.request.contextPath}/index.jsp">Developer Home</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/index.jsp">Admin Home</a>
                </li>
            </ul>
           
        </nav>
        
        <div>
            
            <h1 style="margin-left: 250px">Add User</h1>
            <form  class="admin-form" action="${pageContext.request.contextPath}/Admin" method="post">
                <input type="hidden" name="action" value="addUser"/>
                     
                
                <div>     
                    <label>Username</label>
                    <input type="text" name="username" id="username" class="input"/>
                </div>
                
                <div>
                    <label>Firstname</label>
                    <input type="text" name="firstname" id="firstname" class="input"/>
                </div>
                
                <div>
                    <label>middlename</label>
                    <input type="text" name="middlename" id="middlename"/>
                </div>
                
                <div>
                    <label>Lastname</label>
                    <input type="text" name="lastname" id="lastname"/>
                </div>
                
                <div>
                    <label>Password</label>
                    <input type="text" name="password" id="password"/>
                </div>
                
                <div>
                    <label>Confirm Password</label>
                    <input type="text" name="confPassword" id="confPassword"/>
                </div>
                
                <div>
                    <label for="user_role_select">Role:</label>
                    <select name="user_role_select" id="user_role_select"><!--https://www.geeksforgeeks.org/html/html-select-tag/-->
                      <option value="" disabled selected>Select a user role</option>
                      <option value="ADMIN">Administrator</option>
                      <option value="MEMBER">Member</option>
                    </select>
                </div>
                
                <div class="shift-right">
                <input type="submit" value="Register"/>
                </div>
                
            </form>
            <br>
            <span class="marginLeft300px errors">${errors}</span>
        </div>
                
            </form>
        </div>
        
        <div style="margin-left: 250px; margin-bottom: 80px" id="deleteUserContainer">
            <h1>Delete User</h1>
            <c:import url="/includes/search_for_admin.jsp" />
            <h4><br>Full List of Users</h2>
            <span class="errors">
                <c:forEach var="item" items="${errors}">
                    <tr>
                        <td>[${item.toString()}]</td>
                    </tr>
                 </c:forEach>
            </span><!-- TODO: need to make this look nicer, functionality first though. - RA -->
            <span class="message">
                <c:forEach var="item" items="${messages}">
                    <tr>
                        <td>[${item.toString()}]</td>
                    </tr>
                 </c:forEach>
            </span>
            <span>${userDeletedMessage}</span>
            <br>
            <div class="userList">
                <c:forEach var="item" items="${usersHashMap}">
                    <tr>
                        <td>-> <c:out value="${item.value.getUsername()}" /> </td> <!--<href="parameters passed to controller from url in get request">Part shown to user</a>-->
                    </tr>
                    <br>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
