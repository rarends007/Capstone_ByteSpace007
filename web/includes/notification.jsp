<%-- 
    Document   : notification
    Created on : Apr 10, 2026, 5:57:36 AM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div>
        <form action="${pageContext.request.contextPath}/Member" method="POST">
            
            <input type="image" name="submit" src="${pageContext.request.contextPath}/img/notification_icon_50px.png" border="0" alt="viewNotification" style="width:50px; height: 50px"/>
        </form>
        <!--<!-- https://stackoverflow.com/questions/14199788/how-do-i-use-an-image-as-a-submit-button -->
        <c:choose> 
            <c:when test="insert_test_condition_Here_Soon">
                <p>Your cart is empty.</p> 
            </c:when>
            <c:otherwise>
                <p>Your cart is empty.</p> 
            </c:otherwise>
        </c:choose>
    </div>

</html>
