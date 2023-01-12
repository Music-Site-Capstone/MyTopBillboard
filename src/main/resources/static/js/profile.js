$('.plName').on('click', async function (){
    let myChart=null;
    let playlistId = $(this).attr("plId");
    let username = $(this).attr("activeUser");
    const theURL = "/profile/playlist/";
    let activeUserId = $(this).attr("activeUserId");
    let userId = $(this).attr("userId");


//This utilizes the controller displayPlaylistSong
    let dataF = await fetch(`${theURL}${playlistId}/${username}`).then(res => res.json());
    console.log(dataF);
    console.log("it works")
    console.log(playlistId)
    console.log(username)
    console.log(dataF.rating.length)

    let playlistRatingsLength = dataF.rating.length


    let mappedArr = new Array();
    if (dataF.rating.length == 0) {}
    else{
        for (let i = 0; i < playlistRatingsLength; i++){
            mappedArr.push(dataF.rating[i].score);
        }
    }
    // let ratingArr = new Array();
    // if(dataF.rating.length == 0) {}
    // else{
    //     for (let i = 0; i < playlistRatingsLength; i++){
    //
    //     }
    // }

    console.log(mappedArr)

    let playlistSongsLength = dataF.song.length

    $('#playlist-name').text(dataF.playlistName).attr("plId", playlistId);

    // $('#allPlaylistSongs').text(data.song[0].title);
    // console.log(data.song.length)
    // console.log(playlistSongsLength)
    let csrfValue = document.querySelector('meta[name="_csrf"]').content
    $('#allPlaylistSongs').html('');
    for (let i = 0; i < playlistSongsLength; i++){
        if(activeUserId === userId) {
            $('#allPlaylistSongs').append(`
            <div class="search-line border">
              <form action="/profile/playlist/song/delete/${username}" method="post" class="playlistForm"> 
                              
                  <input type="hidden" name="_csrf" value=${csrfValue}>
                  <input  value=${playlistId} name="userId" type="hidden">
                  <input  value=${dataF.playlistName} name="playlistName" type="hidden">
                  <input  value=${dataF.song[i].id} name="playlistSongId" type="hidden">
              
                <div class="song-container">
                    <div class="image-for-playlist">
                         <img src="${dataF.song[i].image}" alt="fail">   
                    </div>
                    <div class="song-and-tile-playlist">
                        <p> ${dataF.song[i].title} - ${dataF.song[i].artist.artistName} </p>
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
                  <input  value=${dataF.playlistName} name="playlistName" type="hidden">
                  <input  value=${dataF.song[i].id} name="playlistSongId" type="hidden">
              
                <div class="song-container">
                    <div class="image-for-playlist">
                         <img src="${dataF.song[i].image}" alt="fail">    
                    </div>
                    <div class="song-and-tile-playlist">
                        <p> ${dataF.song[i].title} - ${dataF.song[i].artist.artistName} </p>
                    </div>
                </div>
              </form>
            </div>`);
        }
    }



    const labels = "Ratings";
    const data = {
        labels: labels,
        datasets: [{
            label: 'First 6 Playlist Ratings',
            data: [mappedArr[0], mappedArr[1], mappedArr[2], mappedArr[3], mappedArr[4], mappedArr[5], mappedArr[6]],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 1
        }]
    };
    const config = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        },
    };

    const ctx = document.getElementById('myChart');

    if(myChart!=null){
        myChart.destroy();
    }
    myChart = new Chart(ctx, config);



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