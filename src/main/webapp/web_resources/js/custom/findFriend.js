var doFriend = function (input, typeRequest, bool) {
    var tr = $(input).closest('tr')[0];
    var path = $(input).closest('tbody')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.pathForEssenceFriend + '?' + $.param({user_essence_id: tr.dataset.essenceId}),
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
var requestFriend = function (input) {
    doFriend(input, 'POST', true);
};

var removeRequestFriend = function (input) {
    doFriend(input, 'DELETE', false);
};

var friendController = function () {
    if (!this.checked)
        removeRequestFriend(this);
    else requestFriend(this);
};

var init = function () {
    $('.essence-friend-checkbox').on('change', friendController);
};
$(window).on('load', init);