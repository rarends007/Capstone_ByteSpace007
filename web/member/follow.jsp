<%-- Document : followers Created on : Apr 3, 2026, 8:58:50 PM Author : Scout Ernst <se757706@southeast.edu>
    --%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>${title}</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/global.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/member/member.css" />
            </head>

            <body>
                <c:import url="left_panel.jsp" />
                <div class="main_content">
                    <div class="followers_container">
                        <h3>${title}</h3>

                        <p>${noFollow}</p>
                        <ul class="follow_list">
                            <c:forEach items="${follows}" var="follow">
                                <li>${follow.value}
                                    <form action="${pageContext.request.contextPath}/Friends" method="post">
                                        <input type="hidden" name="action" value="removeFollow"><!-- action 1  -->
                                        <input type="hidden" name="title" value="${title}"/>
                                        <input type="hidden" name="followingID" value="${follow.key}">
                                        <input type="submit" value="Remove" class="fake_link">
                                    </form>
                                </li>
                            </c:forEach>
                        </ul>
                        <!--<a href="Friends?action=get$title" class="button_primary reload_btn">Reload list</a> Obsolete RA-->

                        <p>${message}</p>
                    </div>
                </div>
                <c:import url="right_panel.jsp" />
                <script src="${pageContext.request.contextPath}/member/script.js"></script>
            </body>

            </html>