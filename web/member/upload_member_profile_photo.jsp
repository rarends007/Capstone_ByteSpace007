<%-- 
    Document   : upload_photo
    Created on : Mar 20, 2026, 4:05:26 PM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Upload Photo</h1>
        
        <form method="post" action="${pageContext.request.contextPath}/Member" enctype='multipart/form-data'> <!-- https://www.youtube.com/watch?v=c2sZlFeL5bU -->
            <input  type="hidden" value="uploadProfilePhoto"/>
            
            <input type="file" name="file" />
            <input type="submit" value="upload"/>
            
        </form>
        
    </body>
</html>
