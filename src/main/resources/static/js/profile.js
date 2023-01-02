$(function (){
    let regexNumberCheck = / \d/;
    $(`.playlist${regexNumberCheck}`).on('click', function(){
        $('.playlist-title').text($(this).text());
        $('.song-list').html('').append();//??
    })
})