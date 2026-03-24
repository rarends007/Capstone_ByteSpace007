/*
 * Author: rarends100
 */

"use strict";

const message_send_recieve_option = document.querySelector("#messaging_option");

let value = message_send_recieve_option.value;

const switch_values = () => {
    switch (value) {
        case "send":
            console.log("messaging.js -> Send message selected");
            //1. create element nodes
            const send_form = document.createElement("form");
            const choose_recipient = document.createElement("input");
            const message_text = document.createElement("textarea");
            break;
        case "recieve":
            console.log("messaging.js -> Revieve message selected");
            break;
    }
};

document.addEventListener("DOMContentLoaded", () => console.log("Messaging selector DOM loaded"));