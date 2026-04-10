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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
    <nav></nav>
</head>
<body>
    <div class="left_panel">
        <div class="profile_info_container">
            <img src="${pageContext.request.contextPath}/img/profile_bg.png" alt="Profile Background" class="profile_background">
            <img src="${loaded_profile_profile_photo}" alt="Profile Image" class="profile_image" id="profilePicShape">
            <c:import url="upload_member_profile_photo.jsp" />
            <h5 class="profile_name">${loadedProfileUsername}</h5>
            
            <p class="loaded_profile_status">${loadedProfileStatus}</p> 
    
        </div>
        <div class="follow_container">
            <div class="following_container">
                <div class="follow_num">
                    <p>
                        <c:choose>
                            <c:when test="${follow_num != null}">
                                ${numFollowing}
                            </c:when>
                            <c:otherwise>
                                0
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <p>Following</p>
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
                <p>Followers</p>
            </div>
        </div>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/Member">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/Member?action=get_all_users">All Site Users</a></li>
                <li><a href="${pageContext.request.contextPath}/Message">Messages</a></li>
            </ul>
        </nav>
        <a class="logout" href="${pageContext.request.contextPath}/Public?action=logout">Log out</a>
    </div>
    <div class="main_content">
        <div>
            <!-- could add functionality to post to another users profile using this form, likely won't -RA
            <form action="${pageContext.request.contextPath}/Member" method="post" enctype='multipart/form-data'>
                <div class="make_post_input">
                    <img src="${profile_photo}" alt="Profile Image" class="profile_image make_post_img">

                    <input type="hidden" name="action" value="makePost">
                    <input type="hidden" id="userID" name="userID" value="${userID}">
                    <textarea name="postText" id="make_post" placeholder="What’s Happening?"></textarea>
                    <label for="file-upload" class="custom-file-upload"><img src="${pageContext.request.contextPath}/img/image.svg"/></label>
                    <input type="file" name="file"  class="file-upload" id="file-upload"/>
                </div>
                <input type="submit" class="button_primary" value="Post">
            </form>
            -->
            <h5 class="loaded_other_profile_posts_heading">Posts</h5>
        </div>

        <c:forEach var="post" items="${posts}">
            <div class="post_container">
                <div class="post_header">
                    <img src="${loaded_profile_profile_photo}" alt="Profile Image" class="profile_image make_post_img">
                    <h4>${username}</h4>
                </div>
                <p class="post_text"><c:out value="${post.value.postText}"/></p>
                <c:forEach var="image" items="${post.value.images}">
                    <img src="${image.value.imagePath}" class="post_img"/>
                </c:forEach>
                
                <button id="commentsBtn" class="ui_btn"> <img src="${pageContext.request.contextPath}/img/comment.svg"/>${post.value.comments.size()} Comments </button>
                
                <div class="comments_container hidden">
                    <button id="close_comments"><img src="${pageContext.request.contextPath}/img/close.svg"/></button>
                    <c:forEach var="comment" items="${post.value.comments}">
                        <div class="comment_container">
                            <h4 class="comment_heading">${comment.value.commentingUsername}</h4>
                            <p>${comment.value.commentText} </p>   
                            <form action="${pageContext.request.contextPath}/Member" method="post">
                                <input type="hidden" name="action" value="post_comment"/>
                                <input type="hidden" name="post_id" value="${post.value.postID}"/>
                                <textarea name="comment_text" rows="5" cols="35"></textarea>
                                <div>
                                    <button type="submit" value="Reply"/>
                                </div>
                            </form>
                            <form action="${pageContext.request.contextPath}/Member" method="POST">
                                <input type="hidden" name="action" value="delete_comment"/>
                                <input type="hidden" name="comment_id" value="${comment.value.commentID}"/>
                                <div>
                                    <button type="submit" value="Delete"/>
                                </div><!-- delete a comment on click -->
                            </form>
                        </div>
                    </c:forEach>
                    <form action="${pageContext.request.contextPath}/Member" method="post">
                        <input type="hidden" name="action" value="post_comment"/>
                        <input type="hidden" name="post_id" value="${post.value.postID}"/>
                        <textarea name="comment_text" rows="5" cols="35" class="comment_text_input"></textarea>
                        <div>
                            <button type="submit" value="reply" class="button_primary">Reply</button>
                        </div>
                    </form>


                </div>
            </div>
        </c:forEach>

    </div>
    <div class="right_panel"></div>

    <script>
        const profileImg = document.querySelector('#profilePicShape'); // or '.hover'
        const textElem = document.querySelector('#changeImage');
        const uploadForm = document.querySelector('.uplaod_image_form');
        const closeUploadForm = document.querySelector("#close_uplaod_image_form");
        const commentsBtn = document.querySelector("#commentsBtn");
        const commentsContainer = document.querySelector(".comments_container");
        const closeCommentsBtn = document.querySelector("#close_comments");
        const status = document.querySelector(".status");
        const statusForm = document.querySelector(".status_form");

        status.addEventListener('click', () => {
            status.classList.add('hidden');
            statusForm.classList.remove('hidden');


        });

        commentsBtn.addEventListener('click', () => {
            commentsContainer.classList.remove('hidden');
        });

        closeCommentsBtn.addEventListener('click', () => {
            commentsContainer.classList.add('hidden');
        });

        profileImg.addEventListener('click', () => {
            console.log('Profile photo clicked!');
            uploadForm.classList.remove('hidden');
            //window.location.href = "https://www.gfg.com/";
            //                    document.location.href = "${pageContext.request.contextPath}/Member?action=uploadProfilePhoto"; //https://coderanch.com/t/351229/java/calling-servlet-javascript-function

            //https://www.geeksforgeeks.org/html/how-to-get-browser-to-navigate-url-in-javascript/
            //https://www.w3schools.com/js/js_events_mouse.asp
        });

        closeUploadForm.addEventListener('click', () => {
            uploadForm.classList.add('hidden');
        });

    </script>


</body>
</html>
