<%-- Document : right_panel Created on : Apr 13, 2026, 4:00:57 PM Author : ok797738 --%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <div class="right_panel">
                <div class="search_container">
                    <c:import url="/includes/search.jsp"/><!--<!-- Adds functionality to search for users by username dynamically -->
                </div>
                <div class="suggestion_container">
                    <h4>Suggestion for you</h4>
                    <ul class="suggestion_list">
                        <c:forEach var="users" items="${SuggestedUsersHashMap}">
                            <li>
                                <div class="suggestion_content">
                                    <div class="user_avatar">${users.value.getFirstname()},${users.value.getLastname()}
                                    </div>
                                    <a
                                        href="${pageContext.request.contextPath}/Member?action=load_other_profile&userID=${users.value.getUserID()}">
                                        <h4>${users.value.getFirstname()} ${users.value.getLastname()}</h4>
                                        <span class="under_text">${users.value.getUsername()}</span>
                                    </a>
                                </div>
                                <form action="${pageContext.request.contextPath}/Friends" method="post">
                                    <input type="hidden" name="action" value="followUser">
                                    <input type="hidden" name="followingID" value="${loadedProfileUserID}">
                                    <button type="submit" class="follow_btn"><img
                                            src="${pageContext.request.contextPath}/img/add-user.svg"
                                            alt="alt" /></button>
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                    <button class="button_primary view_all_btn"><a href="${pageContext.request.contextPath}/Member?action=get_all_users">View all</a></button>
                </div>
                <c:import url="/includes/notification.jsp" /><!--<!-- Modded to add new notification.jsp - RA -->
                
            </div>