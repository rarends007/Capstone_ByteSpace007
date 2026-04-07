<%-- 
    Document   : user_login_log
    Created on : Mar 30, 2026, 3:48:26 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <details>
            <summary>why?</summary>
            <p>stuff and things</p>
            <p>other stuff and things</p>
        </details>


        <c:forEach items="${usersHashMap}" var="user">
            <details>
                <summary>${user.value.getUserID()} - ${user.value.getUsername()}</summary>
                <table>
                    <thead>
                    <th>Log ID</th>
                    <th>Logged Action</th>
                    <th>Log Notes</th>
                    <th>Logged DateTime</th>
                    </thead>

                    <c:forEach items="${loginMap}" var="log">     
                        <c:if test="${log.value.getLoggedAccountID() == user.value.getUserID()}">
                            <tr>
                                <td>${log.value.getLogID()}</td>
                                <td>${log.value.getLoggedAction()}</td> 
                                <td>${log.value.getLogNotes()}</td>
                                <td>${log.value.getLoggedDateTime()}</td>
                            </tr>
                        </c:if>
                    </c:forEach>

                </table>
            </details>
        </c:forEach>
    </body>
</html>
