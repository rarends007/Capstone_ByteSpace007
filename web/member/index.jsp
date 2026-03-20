<%-- 
    Document   : index
    Created on : Mar 18, 2026, 5:27:08 AM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Profile</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
    </head>
    <body>
        <h1><strong>${username} Home</strong></h1>
        <main>
            <nav class="floatNavRight">
                <ul class="clearFloat">
                    <li>
                        <a href="${pageContext.request.contextPath}/member/index.jsp">Member Home</a>
                    </li>
                </ul>
            </nav>
        </main>
         
        
    </body>
</html>
