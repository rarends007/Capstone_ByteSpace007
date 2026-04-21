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


//let select_value = document.querySelector("#messaging_option")?.value;
//
//document.addEventListener("DOMContentLoaded", () => {
//    console.log("value of select_value: " + select_value);
//
//    select_value = document.querySelector("#messaging_option").value;
//    console.log("\nvalue of select_value: " + select_value);
//
//    if (select_value === "send" || select_value === "received") {
//        console.log("loading send or recieve form");
//
//        document.querySelector("#form").submit();
//    }
//
//});
//
//
//document.querySelector("#messaging_option").addEventListener("click", () => {
//    console.log("message form selector cycled");
//
//    select_value = document.querySelector("#messaging_option").value;
//    console.log("\nvalue of select_value: " + select_value);
//
//    if (select_value === "send" || select_value === "received") {
//        console.log("loading send or recieve form");
//
//        document.querySelector("#form").submit();
//    }
//
//});

const chatAvatars = document.querySelectorAll(".chat_avatar");

chatAvatars.forEach(avatar => {
    const array = avatar.textContent.split(',');
    const abbreviation = array.map(name => name.at(0));
    avatar.textContent = abbreviation.toString().replace(',', '');
});

const chatUsersList = document.querySelectorAll(".chat_user");
const messageСontainers = document.querySelectorAll(".message_container");
const replyId = document.forms.reply_message?.sender_id.value;
const messageTime = document.querySelectorAll(".message_time");

chatUsersList.forEach(user => user.classList.remove("current"));
chatUsersList[0]?.classList.add("current");

messageСontainers.forEach(container => container.classList.add("hidden"));
messageСontainers[0]?.classList.remove("hidden");


function showHideChatByUser(id) {
    if (replyId !== id && replyId) {
        document.forms.reply_message.sender_id.value = id;
        const p = document.querySelector('.reply_message_container p');
        if (p)
            p.textContent = "";

        const span = document.querySelector('.reply_message_container span');
        if (span)
            span.textContent = "";
    }
    if (replyId !== id && !replyId) {
        document.forms.reply_message.sender_id.value = id;
    }
    messageСontainers.forEach(chat => chat.classList.add('hidden'));
    chatUsersList.forEach(user => user.classList.remove('current'));
    document.querySelector(`#chat-${id}`).classList.remove('hidden');
    document.querySelector(`#user-${id}`).classList.add('current');
}

if (replyId) {
    showHideChatByUser(Number(replyId));
}
chatUsersList.forEach(user => {
    const id = Number(user.id.replace('user-', 0));
    user.addEventListener('click', () => showHideChatByUser(id));
});

messageTime.forEach(block => {
    const date = new Date(block.textContent);
    block.textContent = date.toLocaleString();
});


const newChatBtn = document.querySelector('.new_chat');

newChatBtn?.addEventListener('click', () => {
    document.querySelector('.send_message_container').classList.remove('hidden');
});

const userAvatar = document.querySelectorAll(".user_avatar");

userAvatar.forEach(avatar => {
    const array = avatar.textContent.split(',');
    const abbreviation = array.map(name => name.at(0));
    avatar.textContent = abbreviation.toString().replace(',', '');
});

feedUsers = document.querySelectorAll(".feed_user");

feedUsers.forEach(avatar => {
    username = avatar.textContent.trim()[0] + avatar.textContent.trim()[1];
    avatar.textContent = username;
});

const feedToggle = document.querySelector('.feed_toggle');
feedToggle?.addEventListener('click', () => {
    feedToggle.classList.toggle('toggled')
    document.querySelector('#posts')?.classList.toggle('hidden');
    document.querySelector('#feed')?.classList.toggle('hidden');
});


const nav = document.querySelector('nav');
nav?.addEventListener('click', (e) => {
    e.currentTarget.classList.add('current');
})