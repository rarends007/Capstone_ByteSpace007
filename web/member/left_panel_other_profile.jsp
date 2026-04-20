<%-- 
    Document   : left_panel
    Created on : Apr 8, 2026, 3:12:05 PM
    Author     : ok797738
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="left_panel">
    <div class="profile_info_container">
        <img src="${pageContext.request.contextPath}/img/profile_bg.png" alt="Profile Background" class="profile_background">
        <img src="${loaded_profile_profile_photo}" alt="Profile Image" class="profile_image" id="profilePicShape">
       
        <h5 class="profile_name">${loadedProfileUsername}</h5> 

        <p class="loaded_profile_status">${loadedProfileStatus}</p>
    </div>
    <div class="follow_container">
        <div class="following_container">
            <div class="follow_num">
                <p>
                <c:choose>
                    <c:when test="${follow_num != null}">
                        ${numFollowingOther}
                    </c:when>
                    <c:otherwise>
                        0
                    </c:otherwise>
                </c:choose>
                </p>
            </div>
            <p style="color: blueviolet">Following</p>
        </div>
        <div class="following_container">
            <div class="follow_num">
                <p>
                <c:choose>
                    <c:when test="${numFollowers != null}">
                        ${numFollowersOther}
                    </c:when>
                    <c:otherwise>
                        0
                    </c:otherwise>
                </c:choose>
                </p>
            </div>
            <p  style="color: blueviolet">Followers</p>
        </div>
    </div>
    <nav>
        <ul> <!-- Leave same since there is no need to edit options member wise here - RA -->
            <li><a href="${pageContext.request.contextPath}/Member">Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/Member?action=get_all_users">All Site Users</a></li>
            <li><a href="${pageContext.request.contextPath}/Message">Messages</a></li>
        </ul>
    </nav>
    <a class="logout" href="${pageContext.request.contextPath}/Public?action=logout">Log out</a>
</div>