<%-- 
    Document   : gallery
    Created on : Mar 25, 2026, 3:50:22 PM
    Author     : ok797738
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
</head>
<div>
    <form method="post" action="${pageContext.request.contextPath}/Member">
        <input  type="hidden" name="action" value="getImageForUser"/>
        <input type="submit" value="get" class="button_primary"/>
    </form>

    <div class="gallery">
        <c:forEach var="image" items="${gallery}">                     
            <img src="${image}" class="gallery-item"/>
        </c:forEach>
    </div>

</div>