<%-- 
    Document   : search
    Created on : Apr 21, 2026, 8:14:34 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <input type="text" id="search" onkeyup="searchFunction()" placeholder="Search Usernames..." title="Usernames"/>

    <ul id="menu">
        <c:forEach var="userMapItem" items="${userNameSearchMap}">
            <li><a href="Member?action=load_other_profile&&loadedProfileUserID=${userMapItem.value.getUserID()}">${userMapItem.value.getUsername()}</a></li>
            </c:forEach>
    </ul>

    <script> //https://www.w3schools.com/howto/howto_js_search_menu.asp
        function searchFunction() {
            // Declare variables
            var input, filter, ul, li, a, i;
            input = document.getElementById("search");
            filter = input.value.toUpperCase();
            ul = document.getElementById("menu");
            li = ul.getElementsByTagName("li");

            // Loop through all list items, and hide those who don't match the search query
            for (i = 0; i < li.length; i++) {
                a = li[i].getElementsByTagName("a")[0];
                if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    li[i].style.display = "";
                } else {
                    li[i].style.display = "none";
                }
            }
        }
    </script>
</html>
