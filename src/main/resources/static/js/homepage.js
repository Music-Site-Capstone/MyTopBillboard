//homepage search bar to search for profiles
$(document).ready(function () {
    $("#submit").click(function (e) {
        e.preventDefault();
        let formValue = $("#code").val();
        window.location.replace('/profile/' + formValue);
    });
})
