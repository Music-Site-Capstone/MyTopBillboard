//homepage search bar to search for profiles
$(document).ready(function () {
    $("#submit").click(function (e) {
        e.preventDefault();
        let formValue = $("#code").val();
        window.location.replace('/profile/' + formValue);
    });
})

let firstPlace = document.getElementsByClassName('rank-number')[0];
let secondPlace = document.getElementsByClassName('rank-number')[1];
let thirdPlace = document.getElementsByClassName('rank-number')[2];

    $(firstPlace).parent().append('<i class="bi goldTrophy bi-trophy-fill"></i>');
    $(secondPlace).parent().append('<i class="bi silverTrophy bi-trophy-fill"></i>');
    $(thirdPlace).parent().append('<i class="bi bronzeTrophy bi-trophy-fill"></i>');
    $(".rank-number:contains('10')").parent().find('.bi-trophy-fill').remove();





