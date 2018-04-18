const doFriend = function (input, typeRequest, bool) {
    const tr = $(input).closest('tr')[0];
    const path = $('.container')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.contextPath + '/friend_request' + '?' + $.param({user_essence_id: tr.dataset.essenceId}),
        async: false,
        type: typeRequest,
        success: function () {
            $(input).prop('checked', bool);
            bool ?
                $.notify({
                    title: '<strong>Complete!</strong>',
                    message: 'Friend requested'
                }, {
                    type: 'success'
                })
                :
                $.notify({
                    title: '<strong>Complete!</strong>',
                    message: 'Delete request'
                }, {
                    type: 'success'
                })
        },
        error: function (xhr) {
            $(input).prop('checked', !bool);
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Role Essence is not updated status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })
};

const requestFriend = function (input) {
    doFriend(input, 'POST', true);
};

const removeRequestFriend = function (input) {
    doFriend(input, 'DELETE', false);
};

const friendController = function () {
    if (!this.checked)
        removeRequestFriend(this);
    else requestFriend(this);
};

const init = function () {
    $('.essence-friend-checkbox').on('change', friendController);
    initMessageNotification();
};

$(window).on('load', init);