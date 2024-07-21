document.addEventListener('DOMContentLoaded', function () {
    var socket = new SockJS('/ws');
    var stompClient = Stomp.over(socket);
    var currentTargetType = null;
    var currentTargetId = null;

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (messageOutput) {
            showMessage(JSON.parse(messageOutput.body));
        });
    });

    document.querySelector('#messageForm').addEventListener('submit', function (e) {
        e.preventDefault();
        var username = document.querySelector('#username').innerText
        console.log(username)
        var messageInput = document.querySelector('#messageInput');
        var messageContent = messageInput.value.trim();

        if (messageContent && stompClient && currentTargetType && currentTargetId) {
            var chatMessage = {
                sender: username,
                content: messageContent,
                type: 'CHAT',
                targetType: currentTargetType,
                targetId: currentTargetId
            };

            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
            messageInput.value = '';
        }
    });

    // Define the openChat function in the global scope
    window.openChat = function(targetType, targetId) {
        currentTargetType = targetType;
        currentTargetId = targetId;
        console.log(targetId)
        fetch(`/chat/messages?targetType=${targetType}&targetId=${targetId}`)
            .then(response => response.text())
            .then(html => {
                document.querySelector('#chat').innerHTML = html;
                document.querySelector('#messageForm').classList.remove('hidden');
            });
    };

    function showMessage(message) {
        if (message.targetType === currentTargetType && message.targetId === currentTargetId) {
            var messageElement = document.createElement('div');
            messageElement.classList.add('message');

            var messageText = document.createElement('p');
            messageText.classList.add('text-gray-700', 'p-2', 'rounded-lg', 'shadow-md', 'inline-block', 'bg-gray-200', 'mb-2');
            messageText.textContent = message.sender + ': ' + message.content;
            messageElement.appendChild(messageText);

            document.querySelector('#chat').appendChild(messageElement);
            document.querySelector('#chat').scrollTop = document.querySelector('#chat').scrollHeight;
        }
    }
});