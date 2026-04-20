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
    <c:import url="left_panel_other_profile.jsp" />
    <div class="main_content">
        <div class="button-container">
        <form action="${pageContext.request.contextPath}/Friends" method="post">
            <input type="hidden" name="action" value="followUser">
            <input type="hidden" name="followingID" value="${loadedProfileUserID}">
            <input type="submit" class="button_primary" value="Follow">
        </form>
        <form action="${pageContext.request.contextPath}/Block" method="post">
            <input type="hidden" name="action" value="blockUser">
            <input type="hidden" name="blockedUserID" value="${loadedProfileUserID}">
            <input type="submit" class="button_secondary" value="Block">
        </form>
        </div>
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
        <div class="make_post_container">
            <p><strong>Posts</strong></p>
        </div>

        <c:forEach var="post" items="${posts}">
            <div class="post_container">
                <div class="post_header">
                    <img src="${loaded_profile_profile_photo}" alt="Profile Image" class="profile_image make_post_img">
                    <h4>${loadedProfileUsername}</h4> 
                </div>
                <p class="post_text"><c:out value="${post.value.postText}"/></p>
                <c:forEach var="image" items="${post.value.images}">
                    <img src="${image.value.imagePath}" class="post_img"/>
                </c:forEach>
                <button id="commentsBtn-${post.value.postID}" class="ui_btn commentsBtn"> <img src="${pageContext.request.contextPath}/img/comment.svg"/>${post.value.comments.size()} Comments </button>

                <div class="comments_container hidden" id="commentsContainer-${post.value.postID}">
                    <button id="close_comments"><img src="${pageContext.request.contextPath}/img/close.svg"/></button>
                        <c:forEach var="comment" items="${post.value.comments}">
                        <div class="comment_container">
                            <div class="comment_header">
                                <h4 class="comment_heading">${comment.value.commentingUsername}</h4>
                                <c:if test="${loadedProfileUsername.equals(comment.value.commentingUsername)}">
                                    <form action="${pageContext.request.contextPath}/Member" method="POST">
                                        <input type="hidden" name="action" value="delete_comment"/>
                                        <input type="hidden" name="comment_id" value="${comment.value.commentID}"/>
                                        <input type="hidden" name="to_other" value="true"/>
                                        
                                        <button type="submit" value="Delete" class="delete_comment_btn"> <img src="${pageContext.request.contextPath}/img/delete.svg"/> </button>
                                        <!-- delete a comment on click -->
                                    </form>
                                </c:if><!--Protects comments the user did not make from the user deleting them on the viewed profile. -->
                            </div>
                            <p>${comment.value.commentText} </p> 
                           
                            <form action="${pageContext.request.contextPath}/Member" method="post">
                               <input type="hidden" name="action" value="post_comment"/>
                               <input type="hidden" name="post_id" value="${post.value.postID}"/>
                               <input type="hidden" name="to_other" value="true"/>
                               
                               <textarea name="comment_text" rows="5" cols="35" class="reply_text" ></textarea>
                               <button type="submit" class="reply_btn button_primary">Reply</button>
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
    <script src="${pageContext.request.contextPath}/member/script.js"></script>

</body>
</html>

