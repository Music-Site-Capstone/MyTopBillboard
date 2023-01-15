$('.plName').on('click', async function (){
    let playlistId = $(this).attr("plId");
    let username = $(this).attr("activeUser");
    const theURL = "/profile/playlist/";
    let activeUserId = $(this).attr("activeUserId");
    let userId = $(this).attr("userId");
    let theHiddenChartDiv = document.getElementById('myChartContainer');
    theHiddenChartDiv.style.display = "block";


//This utilizes the controller displayPlaylistSong
    let dataF = await fetch(`${theURL}${playlistId}/${username}`).then(res => res.json());
    console.log(dataF);
    console.log(playlistId);
    console.log(username);
    console.log(dataF.rating.length);

    let playlistRatingsLength = dataF.rating.length;

//mapps all ratings of a single playlist into an array
    let mappedArr = [];
    // the following arrays will catch individual ratings from 1-5
    let mappedArrVal1 = [];
    let mappedArrVal2 = [];
    let mappedArrVal3 = [];
    let mappedArrVal4 = [];
    let mappedArrVal5 = [];
    //only want loop to run if the playlist has ratings
    if (dataF.rating.length === 0) {}
    else{
        for (let i = 0; i < playlistRatingsLength; i++){
            mappedArr.push(dataF.rating[i].score);
        }
        //ratings are checked and added to the appropriate array
        const filteredArray = mappedArr.filter(rating => {
            if (rating == 1){
                mappedArrVal1.push(rating);
            } else if (rating == 2){
                mappedArrVal2.push(rating);
            } else if (rating == 3){
                mappedArrVal3.push(rating);
            } else if (rating == 4){
                mappedArrVal4.push(rating);
            } else {
                mappedArrVal5.push(rating);
            }
        })


    }

    console.log(mappedArr);

    let playlistSongsLength = dataF.song.length;

    $('#playlist-name').text(dataF.playlistName).attr("plId", playlistId);


    let csrfValue = document.querySelector('meta[name="_csrf"]').content
    //this line clears the playlist search before loading in a new playlist
    $('#allPlaylistSongs').html('');

    //this loop loads in songs in a playlist
    for (let i = 0; i < playlistSongsLength; i++){
        // you can only delete or add more songs on your own playlists
        if(activeUserId === userId) {
            $('#allPlaylistSongs').append(`
            <div class="search-line border">
              <form action="/profile/playlist/song/delete/${username}" method="post" class="playlistForm"> 
                              
                  <input type="hidden" name="_csrf" value=${csrfValue}>
                  <input  value=${playlistId} name="userId" type="hidden">
                  <input  value=${dataF.playlistName} name="playlistName" type="hidden">
                  <input  value=${dataF.song[i].id} name="playlistSongId" type="hidden">
              
                <div class="song-container d-flex align-items-center justify-content-center">
                    <div class="image-for-playlist">
                    <a href="${dataF.song[i].previewUrl}">
                        <img src="${dataF.song[i].image}" alt="fail">   
                    </a>
                    </div>                    
                    <div class="song-and-tile-playlist text-center mx-4 my-2">
                        <p>${dataF.song[i].artist.artistName}</p>
                        <p>${dataF.song[i].title}</p>          
                    </div>
                    <button>Delete</button>
                </div>
              </form>
            </div>`);
            //else is the version for someone else to view your playlist. delete and add songs will not be added.
        } else {
            $('#allPlaylistSongs').append(`
            <div class="search-line border">
              <form action="/profile/playlist/song/delete/${username}" method="post" class="playlistForm"> 
                              
                  <input type="hidden" name="_csrf" value=${csrfValue}>
                  <input  value=${playlistId} name="userId" type="hidden">
                  <input  value=${dataF.playlistName} name="playlistName" type="hidden">
                  <input  value=${dataF.song[i].id} name="playlistSongId" type="hidden">
              
                <div class="song-container d-flex align-items-center justify-content-center">
                    <div class="image-for-playlist">
                    <a href="${dataF.song[i].previewUrl}">
                        <img src="${dataF.song[i].image}" alt="fail">   
                    </a>
                    </div>
                    <div class="song-and-tile-playlist text-center mx-4 my-2">
                        <p>${dataF.song[i].artist.artistName} </p>
                        <p>${dataF.song[i].title}</p>
                    </div>
                </div>
              </form>
            </div>`);
        }

    }
    // need to append this after the loop to ensure that only one button is created
    if(activeUserId === userId) {
        $('#allPlaylistSongs').append(`
            <a class="nav-link dnModal-button" id="profile-search-music-2">
                Add Music
            </a>`);
    }

    // import Chart from 'chart.js';
    // this loads the data for constructing the graph
    const labels = "12345";
    const data = {
        labels: labels,
        datasets: [{
            label: 'Playlist Ratings',
            data: [mappedArrVal1.length.valueOf(), mappedArrVal2.length.valueOf(), mappedArrVal3.length.valueOf(), mappedArrVal4.length.valueOf(), mappedArrVal5.length.valueOf()],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)'
            ],
            borderWidth: 5
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
    //this removes the previous graph before loading a new one
    const myChart = new Chart(ctx, config);
    $('.plName').on('click', function (e){
        e.preventDefault();
        myChart.destroy();
    })

})




// not sure this does anything
$(function (){
    let regexNumberCheck = / \d/;
    $(`.playlist${regexNumberCheck}`).on('click', function(){
        $('.playlist-title').text($(this).text());
        $('.song-list').html('').append();//??
    })
})