const requestForGettingNewMessages = function () {
    const path = $('.container:first');
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.data('contextPath') + '/messages/new',
        type: "GET",
        contentType: 'application/json',
        success: function (data) {
            const DELAY = 2000;
            let length = data.length === 0 ? 1 : data.length;
            setTimeout(requestForGettingNewMessages, DELAY * length)
            $.each(data, (index, value) => {
                setTimeout(() => $.notify({
                    title: '<strong>New message!</strong>',
                    message: value.text
                }, {
                    placement: {
                        from: "bottom",
                        align: "right"
                    },
                    type: 'info',
                    delay: DELAY * data.length,
                    template: '<div data-notify="container" data-message-id="' + value.messageId + '" class="col-xs-11 col-sm-4 alert alert-{0}" role="alert">' +
                    '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">&times;</button>' +
                    '<button type="button" aria-hidden="true" class="close" data-notify="dismiss" onclick="markMessageAsRead(this)">&check;</button>' +
                    '<span data-notify="title">{1}</span>' + '</br>' +
                    '<span data-notify="message">{2}</span>' +
                    '</div>'
                }), DELAY * index);
            });
        },
        error: function (xhr) {
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Message is not get status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })
};

const markMessageAsRead = (button) => {
    const path = $('.container:first');
    let message = $(button).parent();
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.data('contextPath') + '/messages/new' + '?' + $.param({
            messageId: message.data('messageId'),
            state: 'VIEWED'
        }),
        type: 'PUT',
        error: function (xhr) {
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Message is not marked as read and status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })
};

const initMessageNotification = function () {
    requestForGettingNewMessages();
};
