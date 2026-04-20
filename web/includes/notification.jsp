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
         <!--<!-- https://stackoverflow.com/questions/14199788/how-do-i-use-an-image-as-a-submit-button -->
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

    <div>
        <textarea rows="5" cols="40" readonly="true">
        <c:forEach var="map" items="${notificationsMap}">
        ${map.value.getNotificationInfo()}
        </c:forEach>
        </textarea>
        <form action="${pageContext.request.contextPath}/Notification" method="POST" 
              id="hidden_set_notifcations_viewed">

            <input type="hidden" name="action" value="set_noficiations_viewed"/>
            <input type="hidden" name="submit">
        </form>
    </div>

    </div>

    <script>
        "use strict";
        const notificationsView = document.querySelector("#notification_view");
        const toggleNotificationsButton = document.querySelector("#toggle_notifications_button");
        const form = document.querySelector("#hidden_set_notifcations_viewed");
        
        toggleNotificationsButton.addEventListener("click", () => {
            console.log("toggle notifications button clicked");
            if (notificationsView.classList.contains("hidden")) {
                notificationsView.classList.remove("hidden");
            } else {
                notificationsView.classList.add("hidden");
                form.submit(); //https://www.w3schools.com/jsref/met_form_submit.asp
            }
        });

    </script>

</html>
