<%-- 
    Document   : gallery
    Created on : Mar 25, 2026, 3:50:22 PM
    Author     : ok797738
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/includes/search.css" />

    </head>
    <body>
        <c:import url="left_panel_other_profile.jsp" />
        <div class="main_content">
           
            <div class="gallery">
                <c:forEach var="image" items="${gallery}">                     
                    <img src="${image}" class="gallery-item"/>
                </c:forEach>
            </div>

        </div>
        <c:import url="right_panel.jsp" />
        <script src="${pageContext.request.contextPath}/member/script.js"></script>
    </body>
</html>