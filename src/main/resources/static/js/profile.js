$('.plName').on('click', async function (){
    const ctx = document.getElementById('myChart');
    const myChart = new Chart(ctx)
    let playlistId = $(this).attr("plId");
    let username = $(this).attr("activeUser");
    const theURL = "/profile/playlist/";
    let activeUserId = $(this).attr("activeUserId");
    let userId = $(this).attr("userId");
    myChart.destroy();



//This utilizes the controller displayPlaylistSong
    let data = await fetch(`${theURL}${playlistId}/${username}`).then(res => res.json());
    console.log(data);
    console.log("it works")
    console.log(playlistId)
    console.log(username)
    console.log(data.rating.length)

    let playlistRatingsLength = data.rating.length


    let mappedArr = new Array();
    if (data.rating.length == 0) {}
    else{
        for (let i = 0; i < playlistRatingsLength; i++){
            mappedArr.push(data.rating[i].score);
        }
    }

    console.log(mappedArr)

    let playlistSongsLength = data.song.length

    $('#playlist-name').text(data.playlistName).attr("plId", playlistId);

    // $('#allPlaylistSongs').text(data.song[0].title);
    // console.log(data.song.length)
    // console.log(playlistSongsLength)
    let csrfValue = document.querySelector('meta[name="_csrf"]').content
    $('#allPlaylistSongs').html('');
    myChart.destroy();
    for (let i = 0; i < playlistSongsLength; i++){
        if(activeUserId === userId) {
            $('#allPlaylistSongs').append(`
            <div class="search-line border">
              <form action="/profile/playlist/song/delete/${username}" method="post" class="playlistForm"> 
                              
                  <input type="hidden" name="_csrf" value=${csrfValue}>
                  <input  value=${playlistId} name="userId" type="hidden">
                  <input  value=${data.playlistName} name="playlistName" type="hidden">
                  <input  value=${data.song[i].id} name="playlistSongId" type="hidden">
              
                <div class="song-container">
                    <div class="image-for-playlist">
                         <img src="${data.song[i].image}" alt="fail">   
                    </div>
                    <div class="song-and-tile-playlist">
                        <p> ${data.song[i].title} - ${data.song[i].artist.artistName} </p>
                        <button>delete</button>
                    </div>
                
                </div>
              </form>
            </div>`);
        } else {
            $('#allPlaylistSongs').append(`
            <div class="search-line border">
              <form action="/profile/playlist/song/delete/${username}" method="post" class="playlistForm"> 
                              
                  <input type="hidden" name="_csrf" value=${csrfValue}>
                  <input  value=${playlistId} name="userId" type="hidden">
                  <input  value=${data.playlistName} name="playlistName" type="hidden">
                  <input  value=${data.song[i].id} name="playlistSongId" type="hidden">
              
                <div class="song-container">
                    <div class="image-for-playlist">
                         <img src="${data.song[i].image}" alt="fail">    
                    </div>
                    <div class="song-and-tile-playlist">
                        <p> ${data.song[i].title} - ${data.song[i].artist.artistName} </p>
                    </div>
                </div>
              </form>
            </div>`);
        }


    }

    myChart.destroy();

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['5th', '4th', '3rd', '2nd', 'most recent'],
            datasets: [{
                label: 'last 5 ratings for Playlist',
                data: [mappedArr[0], mappedArr[1] , mappedArr[2], mappedArr[3], mappedArr[4]],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
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