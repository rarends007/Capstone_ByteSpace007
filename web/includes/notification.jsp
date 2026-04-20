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
        <form action="${pageContext.request.contextPath}/Notification" method="GET">
            <!--<!-- https://stackoverflow.com/questions/14199788/how-do-i-use-an-image-as-a-submit-button -->

            <input type="hidden" name="action" value="display_notifications"/>

        </form>
            
            <div  id="toggle_notifications_button">
                <c:choose> 
                <c:when test="${unviewedNotificationsExist}">
                    <input type="image" name="submit" src="${pageContext.request.contextPath}/img/notification_icon_50px_Green.png" 
                           border="0" alt="viewNotification" style="width:50px; height: 50px"/>
                </c:when>
                <c:otherwise>
                    <input type="image" name="submit" src="${pageContext.request.contextPath}/img/notification_icon_50px_red.png" 
                           border="0" alt="viewNotification" style="width:50px; height: 50px"/>
                </c:otherwise>
            </c:choose>
            </div>
    </div>
    <div class="hidden" id="notification_view">
        <c:forEach var="map" items="${notificationsMap}">
            <div>
                <p>${map.value.getNotificationInfo()}<br></p>
            </div>
        </c:forEach>
    </div>

    <script>
            "use strict";
             const notificationsView = document.querySelector("#notification_view");
             const toggleNotificationsButton = document.querySelector("#toggle_notifications_button");
             
             toggleNotificationsButton.addEventListener("click", () => {
                 console.log("toggle notifications button clicked");
                 if(notificationsView.classList.contains("hidden")){
                     notificationsView.classList.remove("hidden");
                 }else{
                     notificationsView.classList.add("hidden");
                 }
             });

    </script>

</html>
