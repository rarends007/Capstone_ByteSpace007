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
    <c:import url="left_panel.jsp" />
    <div class="main_content">
        <div class="make_post_container">
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
        </div>

        <c:forEach var="post" items="${posts}">
            <div class="post_container">
                <div class="post_header">
                    <img src="${profile_photo}" alt="Profile Image" class="profile_image make_post_img">
                    <h4>${username}</h4>
                    <form action="${pageContext.request.contextPath}/Member" method="POST"/>
                    <input type="hidden" name="action" value="delete_post"/>
                    <input type="hidden" name="post_id" value="${post.value.postID}"/>

                    <input type="submit" value="" class="delete_post_btn"/>
                    </form><!-- deletes a post on click -->
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
    <script src="${pageContext.request.contextPath}/member/script.js"></script>

</body>
</html>
