var changeStateFriend = function () {
    var tr = $(this).closest('tr')[0];
    var path = $(this).closest('tbody')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.pathForStateFriend + '?' + $.param({
            user_essence_id: tr.dataset.essenceId,
            state_friend: $(this).find(':selected')[0].value
        }),
        type: 'PUT',
        success: function () {
            $.notify({
                title: '<strong>Complete!</strong>',
                message: 'State friend changed'
            }, {
                type: 'success'
            });
        },
        error: function (xhr) {
            var select = $(tr).find('.action-friends')[0];
            var previewValue = select.dataset.previewValue;
            $(select).find('[value=' + previewValue + ']')[0].setAttribute('selected', true);
            var options = $(select).children();
            for (var i = 0; options.length; i++) {
                if (options[i].value == previewValue) {
                    select.selectedIndex = i;
                    break;
                }
            }
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'State friend is not changed status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })
};
var deleteRequestFriend = function () {
    var tr = this.closest('tr');
    var path = $(this).closest('tbody')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.pathForStateFriend + '?' + $.param({user_essence_id: tr.dataset.essenceId}),
        type: "DELETE",
        success: function () {
            tr.remove();
            $.notify({
                title: '<strong>Complete!</strong>',
                message: 'Request friend deleted'
            }, {
                type: 'success'
            });
        },
        error: function (xhr) {
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Request friend not deleted status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })
};

var setPreviewDataValue = function () {
    this.dataset.previewValue = $(this).find(':selected')[0].value;
};

var init = function () {
    $('.action-friends').on('change', changeStateFriend).on('click', setPreviewDataValue);
    $('.delete-request-friend-of-button').on('click', deleteRequestFriend);
};
$(window).on('load', init);