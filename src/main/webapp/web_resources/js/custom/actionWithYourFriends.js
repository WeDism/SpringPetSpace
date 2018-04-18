const changeStateFriend = function () {
    const tr = $(this).closest('tr')[0];
    const path = $('.container')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.contextPath + '/friend_request' + '?' + $.param({
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
            const select = $(tr).find('.action-friends')[0];
            const previewValue = select.dataset.previewValue;
            $(select).find('[value=' + previewValue + ']')[0].setAttribute('selected', true);
            const options = $(select).children();
            for (let i = 0; options.length; i++) {
                if (options[i].value === previewValue) {
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

const deleteRequestFriend = function () {
    const tr = this.closest('tr');
    const path = $('.container')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.contextPath + '/friend_request' + '?' + $.param({user_essence_id: tr.dataset.essenceId}),
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

const setPreviewDataValue = function () {
    this.dataset.previewValue = $(this).find(':selected')[0].value;
};

const init = function () {
    $('.action-friends').on('change', changeStateFriend).on('click', setPreviewDataValue);
    $('.delete-request-friend-of-button').on('click', deleteRequestFriend);
    initMessageNotification();
};

$(window).on('load', init);