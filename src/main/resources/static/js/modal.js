$(function () {
// When the user clicks on the button, open the modal
    $(document).on('click', '.dnModal-button',function () {
            $('.dnModal, .dnModal-content').removeClass("hidden");
    });

// When the user clicks on <span> (x), close the modal
    $('.dnModal-close').on('click', function () {
        $('.dnModal, .dnModal-content').addClass("hidden");
    });




// When the user clicks anywhere outside of the modal, close it
    let modal = document.getElementsByClassName('dnModal')[0];
    let modalContent = document.getElementsByClassName('.dnModal-content')[0];
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.classList.add("hidden");
            modalContent.classList.add("hidden");
        }
    }
})



$(document).ready(async function(){
    $(".add").on('click', async function(){
        let playlistId = $(this).attr("data-playlist-id");
        let songId = $(this).attr("data-song-id");
        console.log(`About to post a song with an id of ${songId} to playlist ${playlistId}`);
        // $.post("/song/playlist/" + playlistId + "/add/" + songId);
    });
    $(document).on('click', '.addButton', function(){
        let playListId = $('#playlist-name').attr('plid');
        setTimeout(function(){
            console.log(`about to trigger the playlist with the id of ${playListId}`);
            $(`.plName[plid="${playListId}"]`).trigger('click');
        }, 120);
    });
});
