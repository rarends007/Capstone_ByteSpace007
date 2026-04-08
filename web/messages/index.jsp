<%-- 
    Document   : admin_and_member_messages
    Created on : Mar 24, 2026, 4:41:34 PM
    Author     : raren
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messaging</title>
    </head>
    <body>
        <form id="form" action="${pageContext.request.contextPath}/Message" method="get" name="message_form">
            <select name="messaging_option" id="messaging_option" > <!--value of select gets passed to controller as a parameter.-->
                <option value="">--Choose and Option--</option>
                <option value="send">Send Message</option>
                <option value="received">Received Message(s)</option>
            </select>
        </form>
    <c:if test="${option != null}">
        <c:choose>
            <c:when test="${option.equals('send')}">
                <c:import url="/messages/send_message.jsp"/>
            </c:when>
            <c:when test="${option.equals('received')}">
                <c:import url="/messages/recieved_message.jsp"/>
            </c:when>
        </c:choose>
    </c:if>

<span>${messages}</span>
</body>        
<script>
    //   --- https://developer.mozilla.org/en-US/docs/Web/API/HTMLFormElement/submit 
    //this script handles for submission whenever the select element is clicked
    "use strict";

    let select_value = document.querySelector("#messaging_option").value;

    document.addEventListener("DOMContentLoaded", () => {
        console.log("value of select_value: " + select_value);
        
         select_value = document.querySelector("#messaging_option").value;
        console.log("\nvalue of select_value: " + select_value);

        if (select_value === "send" || select_value === "received") {
            console.log("loading send or recieve form");

            document.querySelector("#form").submit();
        }

    });


    document.querySelector("#messaging_option").addEventListener("click", () => {
        console.log("message form selector cycled");

        select_value = document.querySelector("#messaging_option").value;
        console.log("\nvalue of select_value: " + select_value);

        if (select_value === "send" || select_value === "received") {
            console.log("loading send or recieve form");

            document.querySelector("#form").submit();
        }

    });
    

</script>
<!-- Example for easier way to grab form elements
    forms.formname.inputName
    
-->

</html>
