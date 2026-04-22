<%-- Document : left_panel Created on : Apr 8, 2026, 3:12:05 PM Author : ok797738 --%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <div class="left_panel">
                <div class="profile_info_container">
                    <img src="${pageContext.request.contextPath}/img/profile_bg.png" alt="Profile Background"
                        class="profile_background">
                    <c:choose>
                        <c:when test="${profile_photo != null}">
                            <img src="${profile_photo}" alt="Profile Image" class="profile_image"
                                id="profilePicShape">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/img/user.svg" alt="Profile Image"
                                class="profile_image" id="profilePicShape">
                        </c:otherwise>
                    </c:choose>
                    <div class="uplaod_image_form hidden">
                        <div class="uplaod_image_form_header">
                            <h3>Upload Photo</h1>
                                <button id="close_uplaod_image_form">✖️</button>
                        </div>

                        <form method="post" action="${pageContext.request.contextPath}/Member"
                            enctype='multipart/form-data'> <!-- https://www.youtube.com/watch?v=c2sZlFeL5bU -->
                            <input type="hidden" name="action" value="uploadProfilePhoto" />

                            <input type="file" name="file" />
                            <input type="submit" value="upload" class="button_primary" />

                        </form>
                        <div>
                            <c:if test="${messages} != '[]' "><span>${messages}</span></c:if>
                        </div>
                    </div>
                    <h5 class="profile_name">${username}</h5>

                    <c:choose>
                        <c:when test="${!''.equals(userStatus)}">
                            <p class="status">${userStatus}</p>
                        </c:when>
                        <c:otherwise>
                            <p class="status">Click Here to set a Status</p>
                        </c:otherwise>
                    </c:choose>
                    <form action="${pageContext.request.contextPath}/Member" method="post" class="hidden status_form">
                        <input type="hidden" name="action" value="updateStatus">
                        <input type="hidden" id="userID" name="userID" value="${userID}">
                        <input type="text" id="newStatus" name="newStatus">
                        <input type="submit" value="">
                    </form>
                </div>
                <div class="follow_container">
                    <div class="following_container">
                        <div class="follow_num">
                            <p>
                                <c:choose>
                                    <c:when test="${numFollowing != null}">
                                        ${numFollowing}
                                    </c:when>
                                    <c:otherwise>
                                        0
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <p><a href="Friends?action=getFollowing">Following</a></p>
                    </div>
                    <div class="following_container">
                        <div class="follow_num">
                            <p>
                                <c:choose>
                                    <c:when test="${numFollowers != null}">
                                        ${numFollowers}
                                    </c:when>
                                    <c:otherwise>
                                        0
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <p><a href="Friends?action=getFollowers">Followers</a></p>
                    </div>
                </div>
                <nav>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/Member">Profile</a></li>
                        <li><a href="${pageContext.request.contextPath}/Block?action=getBlockedUsers">Blocked Users</a>
                        </li>
                        <c:if test="${role.equals('ADMIN')}">
                            <li><a href="${pageContext.request.contextPath}/Admin">Admin Portal</a></li>
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/Message">Messages</a></li>
                        <li><a href="${pageContext.request.contextPath}/Member?action=getImageForUser">Gallery</a></li>
                    </ul>
                </nav>
                <a class="logout" href="${pageContext.request.contextPath}/Public?action=logout">Log out</a>
            </div>