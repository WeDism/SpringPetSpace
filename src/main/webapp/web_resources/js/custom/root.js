var deleteUser = function () {
    var tr = this.closest('tr');
    var path = $(this).closest('tbody')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.pathForManageUserEssence + tr.dataset.essenceId,
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
var changeRoleUserEssence = function () {
    var tr = this.closest('tr');
    var path = $(this).closest('tbody')[0];
    $.ajax({
        url: location.protocol + '//' + window.location.host + path.dataset.pathForChangeRoleEssence + '?' + $.param({
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
            var previewValue = $(tr).find('.user-essence-roles')[0].dataset.previewValue;
            $(tr).find('[value=' + previewValue + ']')[0].setAttribute('selected', true);
            var select = $(tr).find('.user-essence-roles')[0];
            var options = $(select).children();
            for (var i = 0; options.length; i++) {
                if (options[i].value == previewValue) {
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
var setPreviewDataValue = function () {
    this.dataset.previewValue = $(this).find(':selected')[0].value;
};

var init = function () {
    $('.delete-user-of-button').on('click', deleteUser);
    $('.user-essence-roles').on('change', changeRoleUserEssence).on('click', setPreviewDataValue);
};
$(window).on('load', init);