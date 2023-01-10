$('.plName').on('click', async function (){
    let playlistId = $(this).attr("plId")
    let username = $(this).attr("activeUser")
    const theURL = "/profile/playlist/"

//This utilizes the controller displayPlaylistSong
    let data = await fetch(`${theURL}${playlistId}/${username}`).then(res => res.json());
    console.log(data);
    console.log("it works")
    console.log(playlistId)
    console.log(username)

    let playlistSongsLength = data.song.length

    $('#playlist-name').text(data.playlistName).attr("plId", playlistId);

    // $('#allPlaylistSongs').text(data.song[0].title);
    // console.log(data.song.length)
    console.log(playlistSongsLength)

    $('#allPlaylistSongs').html('');
    for (let i = 0; i < playlistSongsLength; i++){
        $('#allPlaylistSongs').append(`
        <div class="search-line border">
            <div class="song-container">
                 <div class="image-for-playlist">
                     <img src="${data.song[i].image}" alt="fail">   
                     
                </div>
                <div class="song-and-tile-playlist">
                <p> ${data.song[i].title} - ${data.song[i].artist.artistName} </p>
                </div>
            
            </div>
           
        </div>`);
        console.log(data.song[i].image)
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