//homepage search bar to search for profiles
$(document).ready(function () {
    $("#submit").click(function (e) {
        e.preventDefault();
        let formValue = $("#code").val();
        window.location.replace('/profile/' + formValue);
    });
})

$(".rank-number:contains('1')").parent().append('<i class="bi goldTrophy bi-trophy-fill"></i>');
$(".rank-number:contains('2')").parent().append('<i class="bi silverTrophy bi-trophy-fill"></i>');
$(".rank-number:contains('3')").parent().append('<i class="bi bronzeTrophy bi-trophy-fill"></i>');
$(".rank-number:contains('10')").parent().find('.bi-trophy-fill').remove();
