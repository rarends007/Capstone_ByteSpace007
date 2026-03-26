<%-- 
    Document   : upload_photo
    Created on : Mar 20, 2026, 4:05:26 PM
    Author     : raren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="uplaod_image_form hidden">
    <div class="uplaod_image_form_header">
        <h3>Upload Photo</h1>
            <button id="close_uplaod_image_form">✖️</button>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/Member" enctype='multipart/form-data'> <!-- https://www.youtube.com/watch?v=c2sZlFeL5bU -->
        <input  type="hidden" name="action" value="uploadProfilePhoto"/>

        <input type="file" name="file" />
        <input type="submit" value="upload" class="button_primary"/>

    </form>
    <div>
        <span>${messages}</span>
    </div>

</div>