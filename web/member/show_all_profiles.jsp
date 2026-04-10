<%-- 
    Document   : show_all_profiles
    Created on : Apr 10, 2026, 1:42:18 AM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Profiles</title>
    </head>
    <body>
        <h1>All Profiles</h1>
        <ul>
            <c:forEach var="users" items="${allUsersHashMap}">
                <li><a href="${pageContext.request.contextPath}/Member?action=load_other_profile&userID=${users.value.getUserID()}">${users.value.getUsername()}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
