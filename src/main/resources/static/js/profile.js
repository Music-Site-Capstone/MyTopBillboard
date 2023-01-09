$('.plName').on('click', async function (){
    let playlistId = $(this).attr("plId")
    let username = $(this).attr("activeUser")
    const theURL = "/profile/playlist/"


    let data = await fetch(`${theURL}${playlistId}/${username}`).then(res => res.json());
    console.log(data);
    console.log("it works")
    console.log(playlistId)
    console.log(username)

    let playlistSongsLength = data.song.length

    $('#playlist-name').text(data.playlistName)
    // $('#allPlaylistSongs').text(data.song[0].title);
    // console.log(data.song.length)
    console.log(playlistSongsLength)
    $('#allPlaylistSongs').html('');
    for (let i = 0; i < playlistSongsLength; i++){
        $('#allPlaylistSongs').append(`<div class="search-line"><p> ${data.song[i].title} - ${data.song[i].artist.artistName} </p></div>`);
    }


})

$(function (){
    let regexNumberCheck = / \d/;
    $(`.playlist${regexNumberCheck}`).on('click', function(){
        $('.playlist-title').text($(this).text());
        $('.song-list').html('').append();//??
    })

    // $('#newPlaylistInput').on('keypress',function(e) {
    //     if(e.which === 13 || e.keyCode === 13) {
    //         // $.ajax("/newPlaylistRequest", {
    //         //     type: POST,
    //         //     data: {
    //         //         playlistName: $(this).val()
    //         //     }
    //         // })
    //         $('.playlistForm').submit(function (event){
    //             event.preventDefault();
    //         })
    //     }
    // });
})