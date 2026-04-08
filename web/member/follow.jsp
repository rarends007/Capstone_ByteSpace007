<%-- 
    Document   : followers
    Created on : Apr 3, 2026, 8:58:50 PM
    Author     : Scout Ernst <se757706@southeast.edu>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
    </head>
    <body>
        <div class=".container">
            <h3>${title}</h3>
            <ul>
                <c:forEach items="${follows}" var="follows">
                    <li>${follows.value}</li>
                </c:forEach>
            </ul>
            <p>${message}</p>
        </div>
    </body>
</html>
