var sendMessage = function () {
    var textArea = $("textarea:first");
    var message = createMessage(textArea);
    var path = $(this).closest('.card')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.pathForMessage,
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify(message),
        success: function () {
            textArea.val(null);
            addLiInUl(message, path.dataset.authorNickname, path.dataset.pathToProfile);
            $.notify({
                title: '<strong>Complete!</strong>',
                message: 'Message sent'
            }, {
                type: 'success'
            });
        },
        error: function (xhr) {
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Message is not sent status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })
};
var createMessage = function (textArea) {
    var message = {};
    message.messageId = null;
    message.text = textArea.val();
    message.date = null;
    message.author = null;
    var array = $("input:checked");
    message.owners = [];
    for (var i = 0; i < array.length; i++) {
        message.owners.push(array[i].id);
    }
    return message;
};
var addLiInUl = function (message, authorNickname, pathToProfile) {
    var date = moment();
    $('.list-unstyled:last div:first')
        .before($('<div></div>').addClass('row')
            .append($('<div></div>').addClass('col-6 offset-6')
                .append($('<li></li>').addClass('media')
                    .append($('<div></div>').addClass('media-body')
                        .append($('<div></div>').addClass('text-muted float-right')
                            .append($('<small></small>')
                                .append($('<div></div>').addClass('float-right').attr('title', date.format('HH:mm:ss')).html('&nbsp;' + date.format('YYYY-MM-DD')))
                                .append($('<a></a>').addClass('float - right').attr('href', pathToProfile).text('author ' + authorNickname))))
                        .append($('<strong></strong>').addClass('text-success').text('to ' + message.owners.sort((a, b) => a > b).toString()))
                        .append($('<p></p>').text(message.text))))));
};
var init = function () {
    $('#sendMessage').on('click', sendMessage);
};
$(window).on('load', init);