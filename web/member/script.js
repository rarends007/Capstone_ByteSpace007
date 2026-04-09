/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const profileImg = document.querySelector('#profilePicShape'); // or '.hover'
const textElem = document.querySelector('#changeImage');
const uploadForm = document.querySelector('.uplaod_image_form');
const closeUploadForm = document.querySelector("#close_uplaod_image_form");
const commentsBtn = document.querySelectorAll(".commentsBtn");
const closeCommentsBtn = document.querySelectorAll("#close_comments");
const status = document.querySelector(".status");
const statusForm = document.querySelector(".status_form");

status?.addEventListener('click', () => {
    status.classList.add('hidden');
    statusForm.classList.remove('hidden');


});

commentsBtn.forEach(btn => {
    btn.addEventListener('click', (e) => {
        id = e.target.id.replace('commentsBtn-', '');
        commentsContainer = document.querySelector(`#commentsContainer-${id}`);
        commentsContainer.classList.remove('hidden');
    });
});
closeCommentsBtn.forEach(btn => {
    btn?.addEventListener('click', () => {
        commentsContainer = document.querySelectorAll('.comments_container');
        commentsContainer.forEach(cont => cont.classList.add('hidden'));
    });
});

profileImg?.addEventListener('click', () => {
    console.log('Profile photo clicked!');
    uploadForm.classList.remove('hidden');
    //window.location.href = "https://www.gfg.com/";
    //                    document.location.href = "${pageContext.request.contextPath}/Member?action=uploadProfilePhoto"; //https://coderanch.com/t/351229/java/calling-servlet-javascript-function

    //https://www.geeksforgeeks.org/html/how-to-get-browser-to-navigate-url-in-javascript/
    //https://www.w3schools.com/js/js_events_mouse.asp
});

closeUploadForm?.addEventListener('click', () => {
    uploadForm.classList.add('hidden');
});


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