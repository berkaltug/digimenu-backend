
var audio = new Audio("../sounds/door_bell.mp3");
var queue = [];
var queueindex = 1;
var zindex = 0;
var masaNo;
var messageId;
var socket;
var stompClient;
var recInterval = null;

$(document).ready(function () {
    renderSocketMessage();
});

var connectCallback = function (frame) {
    queueindex--;
    recallMessages();
    stompClient.subscribe("/user/restaurant/message", function (data) {
        databody = JSON.parse(data.body);
        html = '<div class="modal"  role="dialog" id="dynamicModal' + (queueindex).toString() + '" style="{z-index:' + (zindex -= 1061).toString() + '}" >';
        html += '<div class="modal-dialog" role="document">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<h5 class="modal-title">Yeni Bildirim</h5>';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
        html += '<span aria-hidden="true">&times;</span>';
        html += '</button>';
        html += '</div>';
        html += '<div class="modal-body text-center">';
        html += '<p>' + databody.message + '</p>';
        html += '</div>';
        html += '<div class="modal-footer">';
        html += '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        queue.push(html);
        var modal = queue.shift();
        $('body').append(modal);
        $(modal).modal();

        audio.play();
        masaNo = databody.masaNo;
        deleteMessageFromTable(databody.messageId);
    });
}

var errorCallback = function (frame) {
    console.log("error callback içinde");
    //setTimeout(renderSocketMessage, 10000);
    //console.log("10 sn içinde tekrar denenecek");
}

function renderSocketMessage() {
    console.log("tries to reconnect")
    socket = new SockJS('/stomp');
    stompClient = Stomp.over(socket);
    clearInterval(recInterval);

    stompClient.connect({}, connectCallback, errorCallback);

    socket.onclose = function () {
        console.log("socket.onclose içerisinde");
        socket = null;
        stompClient.disconnect();
        recInterval = setInterval(function () {
            renderSocketMessage();
        }, 5000)
    }

}


function deleteMessageFromTable(messageId) {
    $.ajax({
        type: "GET",
        async: false,
        contentType: "application/json",
        xhrFields: {
            useCredentials: true,
        },
        url: "/restaurant/mesaj/" + messageId,
        success: () => {
        }
    });
}

function recallMessages() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        xhrFields: {
            useCredentials: true,
        },
        url: "/restaurant/recallMessages/",
        success: () => {
        }
    });
}