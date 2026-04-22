<%-- Document : notification Created on : Apr 10, 2026, 5:57:36 AM Author : raren --%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" type="text/css"
                    href="${pageContext.request.contextPath}/includes/notificationStyle.css" />
            </head>
            <div class="notification_container">
                <div class="hidden" id="notification_view">

                    <div>
                        <textarea rows="10" cols="40" readonly="true"
                            placeholder="No Notifications yet. We'll keep you posted!" wrap="soft">
        <c:forEach var="map" items="${notificationsMap}">
        ${map.value.getNotificationInfo()}
       --------------------------
       --------------------------
        </c:forEach>
        </textarea>
                        <form action="${pageContext.request.contextPath}/Notification" method="POST"
                            id="hidden_set_notifcations_viewed">
                            <!--<!-- When the notfication is clicked twice the JS calls 
                                                   the forms submit method which is not overridden by a submit button or element with the name submit. -->
                            <input type="hidden" name="action" value="set_noficiations_viewed" />
                        </form>
                    </div>

                </div>
                <div>
                    <!--<!-- https://stackoverflow.com/questions/14199788/how-do-i-use-an-image-as-a-submit-button -->
                    <div id="toggle_notifications_button">
                        <c:choose>
                            <c:when test="${unviewedNotificationsExist}">
                                <input type="image" name="submit" src="${pageContext.request.contextPath}/img/bell.svg"
                                    border="0" alt="viewNotification" style="width:50px; height: 50px"
                                    class="notifications_bell unviewed_notifications" />
                            </c:when>
                            <c:otherwise>
                                <input type="image" name="submit" src="${pageContext.request.contextPath}/img/bell.svg"
                                    border="0" alt="viewNotification" style="width:50px; height: 50px"
                                    class="notifications_bell" />
                            </c:otherwise>
                        </c:choose>
                    </div>
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