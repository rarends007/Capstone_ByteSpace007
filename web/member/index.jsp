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
    </head>
    <body>
        <div class="left_panel">
            <div class="profile_info_container">
                <img src="${pageContext.request.contextPath}/img/profile_bg.png" alt="Profile Background" class="profile_background">
                <img src="${profile_photo}" alt="Profile Image" class="profile_image" id="profilePicShape">
                <c:import url="upload_member_profile_photo.jsp" />
                <h5 class="profile_name">${username}</h5>
                <p class="status">Developing UI for our group project...</p>
            </div>
            <div class="follow_container">
                <div class="following_container">
                    <div class="follow_num">
                        5,432
                    </div>
                    <p>Following</p>
                </div>
                <div class="following_container">
                    <div class="follow_num">
                        7,345
                    </div>
                    <p>Followers</p>
                </div>
            </div>
            <nav>
                <ul>
                    <li>Profile</li>
                    <li>Friends</li>
                    <li>Messages</li>
                </ul>
            </nav>
            <a class="logout">Log out</a>
        </div>
        <div class="main_content">
            <div class="make_post_container">
                <div class="make_post_input">
                    <img src="" alt="Profile Image" class="profile_image make_post_img">
                    <textarea name="make_post" id="make_post" placeholder="What’s Happening?"></textarea>
                </div>
                <button class="button_primary">Post</button>
            </div>
            
            <span id="changeImage" class="alterSpan"></span>
            
            
            <div> <!-- user status would most likely go here. -- Kyle -- -->
                <p></p>
                <form>
                    
                    <input type="submit" action="">
                </form>
            </div>
            
            
            <div id="middleColumnContainer"> <!-- profile feed will go here -->
                <div id="profileFeedHeader"><Strong>Profile Feed</Strong></div>
                <p>
                    Lorem ipsum dolor Integer pelLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. 
                </p>
                <c:forEach var="post" items="${posts}">
                <div>
                    <c:forEach var="image" items="${post.value.images}">
                        <img src="${image.value.imagePath}"/>
                    </c:forEach>
                    <p><c:out value="${post.value.postText}"/></p>
                </div>
                <div>
                    <c:forEach var="comment" items="${post.value.comments}">
                        <div>
                        <h4>${comment.value.commentingUsername}</h4>
                        <p>${comment.value.commentText}</p>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
            </div>
            <div class="test"></div>
        </div>
        <div class="right_panel"></div>




        <!--        <main>
                    <nav id="floatProfileNavRight">
                        <ul class="clearFloat" id="profileNavUl">
                            <li class="profileNavLi">
                                <a href="${pageContext.request.contextPath}/Member">Member Home</a>
                            </li>
                            <li class="profileNavLi">
                                <a href="${pageContext.request.contextPath}/Public?action=logout">Logout</a>
                            </li>
                            <li class="profileNavLi">
                                <a href="TODO" >Main Feed</a>
                            </li>
                            <li class="profileNavLi">
                                <a href="TODO" >Gallery</a>
                            </li>
                            <li class="profileNavLi">
                                <a href="TODO" >Profile Settings</a>
                            </li>
                        </ul>
                    </nav>
                    
                    <div id="profilePicPostion" >
                        <h1 id="shiftProfileUsername"><strong>${username}</strong></h1>        
                        <img class="hover changeProfilePhoto" id="profilePicShape" src="${profile_photo}" alt="profile pic" height="300px" width="300px"/>
                    </div>
                    
                    <span id="changeImage" class="alterSpan"></span>
                    
                    <div id="middleColumnContainer">  profile feed will go here 
                        <div id="profileFeedHeader"><Strong>Profile Feed</Strong></div>
                        <p>
                            Lorem ipsum dolor Integer pelLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metusLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impeLorem ipsum dolor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impe a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impe a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impe a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impe a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impe a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impe a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impe a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui impelentesque elementum nisl, sed ullamcorper lectus auctor Integer pellentesque elementum nisl, sed ullamcorper lectus auctor sed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui imperdiet id. Mauris aliquam, purus a accumsan accumsan, neque tortor lobortis nulla, quis pretium metus nunc sed urna. Praesent condimentum sapien eros, in interdum dui ornare ac. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec volutpat consectetur ante, quis rutrum dolor mattis ac. Fusce id neque eget ipsum pulvinar posuere nec vitae risus. Pellentesque ullamcorper vel elit et suscipit. Etiam vel neque malesuada nisl interdum congue. Ut posuere metus vitae vehicula iaculis. Suspendisse et elementum elit, nec imperdiet ligula. Aenean libero arcu, ultrices ac tempor a, auctor at nulla. Etiam consectetur felis ipsum, sed egestassed. Phasellus nec metus a risus mollis semper id et ante. Suspendisse aliquet, felis eu sodales ullamcorper, magna odio varius lectus, non hendrerit diam dui quis massa. Praesent sagittis erat augue, quis mollis dui imperdiet id. Mauris aliquam, purus a accumsan accumsan, neque tortor lobortis nulla, quis pretium metus nunc sed urna. Praesent condimentum sapien eros, in interdum dui ornare ac. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec volutpat consectetur ante, quis rutrum dolor mattis ac. Fusce id neque eget ipsum pulvinar posuere nec vitae risus. Pellentesque ullamcorper vel elit et suscipit. Etiam vel neque malesuada nisl interdum congue. Ut posuere metus vitae vehicula iaculis. Suspendisse et elementum elit, nec imperdiet ligula. Aenean libero arcu, ultrices ac tempor a, auctor at nulla. Etiam consectetur felis ipsum, sed egestassit amet, consectetur adipiscing elit. Donec pellentesque varius erat, non maximus lacus venenatis ac. Praesent et egestas nisl. Sed nec massa ut nulla efficitur viverra eu vitae dui. Mauris neque eros, elementum porttitor quam ut, vulputate tempus nibh. Suspendisse quis nibh at tellus convallis ornare sollicitudin vel velit. Quisque eu dapibus nulla. Phasellus vel augue tristique, ullamcorper erat non, malesuada sem. Proin varius, felis vitae pulvinar pretium, metus ligula auctor lectus, ut fermentum erat sem sed risus. Vestibulum ultricies eget mi scelerisque efficitur.
                        </p>
                    </div>
                    
                    <div class="clearFloat">
                        
                    </div>
                            
                </main>-->

        <script>
            const profileImg = document.querySelector('#profilePicShape'); // or '.hover'
            const textElem = document.querySelector('#changeImage');
            const uploadForm = document.querySelector('.uplaod_image_form');   
            const closeUploadForm = document.querySelector("#close_uplaod_image_form");


            profileImg.addEventListener('click', () => {
            console.log('Profile photo clicked!');
                    uploadForm.style.display = 'block';
            //window.location.href = "https://www.gfg.com/";
//                    document.location.href = "${pageContext.request.contextPath}/Member?action=uploadProfilePhoto"; //https://coderanch.com/t/351229/java/calling-servlet-javascript-function

            //https://www.geeksforgeeks.org/html/how-to-get-browser-to-navigate-url-in-javascript/
            //https://www.w3schools.com/js/js_events_mouse.asp
        });
        
        closeUploadForm.addEventListener('click', () => {
            uploadForm.style.display = 'none';
        });
        
        </script>


    </body>
</html>
