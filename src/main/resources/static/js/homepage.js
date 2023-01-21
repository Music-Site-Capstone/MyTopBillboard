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

    $(firstPlace).text("").append('<i class="bi goldTrophy bi-trophy-fill"></i>').parent().parent().parent().css('border', '2px solid gold');
    $(secondPlace).text("").append('<i class="bi silverTrophy bi-trophy-fill"></i>').parent().parent().parent().css('border', '2px solid silver');
    $(thirdPlace).text("").append('<i class="bi bronzeTrophy bi-trophy-fill"></i>').parent().parent().parent().css('border', '2px solid #CD7F32');
    $(".rank-number:contains('10')").parent().find('.bi-trophy-fill').remove();





