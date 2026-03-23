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
        
        <main>
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

            <div class="clearFloat">
                
            </div>
                    
        </main>
            
        <script> 
                const profileImg = document.querySelector('#profilePicShape'); // or '.hover'
                const textElem   = document.querySelector('#changeImage');

                if (profileImg && textElem) {
                    profileImg.addEventListener('mouseenter', () => {
                        textElem.textContent = 'change profile photo';
                    });

                    profileImg.addEventListener('mouseleave', () => {
                        textElem.textContent = '';
                    });

                    profileImg.addEventListener('click', () => {
                        console.log('Profile photo clicked!');
                        //window.location.href = "https://www.gfg.com/";
                        document.location.href = "${pageContext.request.contextPath}/Member?action=uploadProfilePhoto"; //https://coderanch.com/t/351229/java/calling-servlet-javascript-function
                    });
                }
                //https://www.geeksforgeeks.org/html/how-to-get-browser-to-navigate-url-in-javascript/
                //https://www.w3schools.com/js/js_events_mouse.asp
        </script>
         
        
    </body>
</html>
