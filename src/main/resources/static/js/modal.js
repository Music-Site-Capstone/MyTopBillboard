$(function () {
// When the user clicks on the button, open the modal
    $('.dnModal-button').on('click' , function (event) {
        console.log(event);
        $(event.target.nextElementSibling.nextElementSibling).css('display', "block");
    });

// When the user clicks on <span> (x), close the modal
    $('.dnModal-close').on('click', function () {
        $('.dnModal').css('display', "none");
    });


// When the user clicks anywhere outside of the modal, close it
    const modal = document.getElementsByClassName('dnModal')[0];
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

})

$(document).ready(function(){
    $(".add").on('click', function(){
        let playlistId = $(this).attr("data-playlist-id");
        let songId = $(this).attr("data-song-id");
        $.post("/song/playlist/" + playlistId + "/add/" + songId);
    });
});