const deleteUser = function () {
    const tr = this.closest('tr');
    const path = $('.container')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.contextPath + '/profile/' + tr.dataset.essenceId,
        type: "DELETE",
        success: function () {
            tr.remove();
            $.notify({
                title: '<strong>Complete!</strong>',
                message: 'Role Essence is deleted'
            }, {
                type: 'success'
            });
        },
        error: function (xhr) {
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Role Essence is not updated status: ' + xhr.status
            }, {
                type: 'danger'
            });
        }
    })
};

const changeRoleUserEssence = function () {
    const tr = this.closest('tr');
    const path = $('.container')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.contextPath + '?' + $.param({
            userId: tr.dataset.essenceId,
            roleEssenceEnum: $(this).find(':selected')[0].value
        }),
        type: "PUT",
        success: function () {
            $.notify({
                title: '<strong>Complete!</strong>',
                message: 'Role Essence is updated'
            }, {
                type: 'success'
            });
        },
        error: function (xhr) {
            const previewValue = $(tr).find('.user-essence-roles')[0].dataset.previewValue;
            $(tr).find('[value=' + previewValue + ']')[0].setAttribute('selected', true);
            const select = $(tr).find('.user-essence-roles')[0];
            const options = $(select).children();
            for (let i = 0; options.length; i++) {
                if (options[i].value === previewValue) {
                    select.selectedIndex = i;
                    break;
                }
            }
            $.notify({
                title: '<strong>Error!</strong>',
                message: 'Role Essence is not updated status: ' + xhr.status
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
    $('.delete-user-of-button').on('click', deleteUser);
    $('.user-essence-roles').on('change', changeRoleUserEssence).on('click', setPreviewDataValue);
    initMessageNotification();
};
$(window).on('load', init);