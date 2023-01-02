$(function (){
    let regexNumberCheck = / \d/;
    $(`.playlist${regexNumberCheck}`).on('click', function(){
        $('.playlist-title').text($(this).text());
        $('.song-list').html('').append();//??
    })

    $('#newPlaylistInput').on('keypress',function(e) {
        if(e.which === 13 || e.keyCode === 13) {
            $('.playlistForm').submit();
        }
    });
})