$('.list-group-item').each(function(index) {
    if (index % 2 !== 0) {
        $(this).css('background-color', '#575757b0')
    }
});
const profile = {
    playlistId: "",
    username: "",
    activeUserId: "",
    userId: "",
    dataF: "",
    playlistUpdate() {
        let playlistSongsLength = profile.dataF.song.length;
        let csrfValue = document.querySelector('meta[name="_csrf"]').content
        $('#playlist-name').text(profile.dataF.playlistName).attr("plId", profile.playlistId);

        //this line clears the playlist search before loading in a new playlist
        $('#allPlaylistSongs').html('');
        $('#toxicUsers').html('');
        $('#userContainerName').html('');

        //this loop loads in songs in a playlist
        for (let i = 0; i < playlistSongsLength; i++) {
            // you can only delete or add more songs on your own playlists
            if (profile.activeUserId === profile.userId) {
                $('#allPlaylistSongs').append(`
                    <div class="search-line border">                                      
                        <input type="hidden" name="_csrf" value=${csrfValue}>
                        <input  value=${profile.playlistId} name="playlistId" type="hidden">
                        <input  value=${profile.dataF.playlistName} name="playlistName" type="hidden">
                        <input  value=${profile.dataF.song[i].id} name="playlistSongId" type="hidden">
                      
                        <div class="song-container">
                            <div class="image-for-playlist">
                                <a href="${profile.dataF.song[i].previewUrl}">
                                    <img src="${profile.dataF.song[i].image}" alt="fail">   
                                </a>
                            </div>
                            <div class="song-and-title-playlist">
                                <p> ${profile.dataF.song[i].title} - ${profile.dataF.song[i].artist.artistName} </p>
                                <div class="icon-wrapper" searchId="target">
                                  <div class="lid"></div>
                                  <div class="can"></div>
                                </div>
                            </div>
                        </div>
                    </div>`);
                //else is the version for someone else to view your playlist. delete and add songs will not be added.
            } else {
                $('#allPlaylistSongs').append(`
                    <div class="search-line border">
                        <div class="song-container">
                            <div class="image-for-playlist">
                                <a href="${profile.dataF.song[i].previewUrl}">
                                    <img src="${profile.dataF.song[i].image}" alt="fail">   
                                </a> 
                            </div>
                            <div class="song-and-title-playlist" id="song-and-title-playlist">
                                <p  class="title-color"> ${profile.dataF.song[i].title} - ${profile.dataF.song[i].artist.artistName} </p>
                            </div>
                        </div>
                    </div>`);
            }
        }

        // need to append this after the loop to ensure that only one button is created
        if (profile.activeUserId === profile.userId) {
            $('#addButton').html("").append(`
            <a class="nav-link dnModal-button" id="profile-search-music-2">
                Add Music
            </a>`);
        }
    },

    graphUpdate(){
        //maps all ratings of a single playlist into an array
        let mappedArr = [];

        // the following arrays will catch individual ratings from 1-5
        let mappedArrVal1 = [];
        let mappedArrVal2 = [];
        let mappedArrVal3 = [];
        let mappedArrVal4 = [];
        let mappedArrVal5 = [];
        // let getUsernamesFromId =

        //only want loop to run if the playlist has ratings
        if (profile.dataF.rating.length > 0) {
            for (let i = 0; i < profile.dataF.rating.length; i++) {
                mappedArr.push(profile.dataF.rating[i].score);

            }
            //ratings are checked and added to the appropriate array
            const filteredArray = mappedArr.filter(rating => {
                if (rating == 1) {
                    mappedArrVal1.push(rating);
                } else if (rating == 2) {
                    mappedArrVal2.push(rating);
                } else if (rating == 3) {
                    mappedArrVal3.push(rating);
                } else if (rating == 4) {
                    mappedArrVal4.push(rating);
                } else {
                    mappedArrVal5.push(rating);
                }
            })
        }


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
            }
        };

        const ctx = document.getElementById('myChart');
        //this removes the previous graph before loading a new one
        const myChart = new Chart(ctx, config);
        $('.plName').on('click', function (e) {
            e.preventDefault();
            myChart.destroy();
        })
    },
    exposeUsersWhoRated(){
        let toxicUserIds = [];
        if (profile.dataF.rating.length > 0) {
            for (let i = 0; i < profile.dataF.rating.length; i++) {
                let userId = profile.dataF.rating[i].userId;
                if (!toxicUserIds.includes(userId)) {
                    toxicUserIds.push(userId);
                }
            }

            Promise.resolve().then(() => {
                $('#userContainerName').append(`
                        <div class="search-line border" >
                            Users Who Rated Playlist
                        </div>`);
                for (const value of toxicUserIds) {
                    console.log(toxicUserIds)
                    console.log(value)
                    const exposingUsers = fetch(`/profile/playlist/${value}`)
                        .then(response => response.json())
                        .then(dataT => {
                            console.log(dataT)
                            console.log(dataT.username)
                            $('#toxicUsers').append(`
                        <div class="search-line shadow">
                            <div class="song-container shadow border" id="showsUsersContainer">                                                                                                 
                                    <a class="userRaters" href="${dataT.username}"> ${dataT.username}</a>                    
                            </div>
                        </div>`);
                        })
                        .catch(error => console.error(error));
                }
            });
        }
    }

}


$('.plName').on('click',async function () {
    profile.playlistId = $(this).attr("plId");
    profile.username = $(this).attr("activeUser");
    profile.activeUserId = $(this).attr("activeUserId");
    profile.userId = $(this).attr("userId");

//This utilizes the controller displayPlaylistSong
    profile.dataF = await fetch(`/profile/playlist/${profile.playlistId}/${profile.username}`).then(res => res.json());
    let theHiddenChartDiv = document.getElementById('myChartContainer');
    theHiddenChartDiv.style.display = "block";
    profile.playlistUpdate();
    profile.graphUpdate();
    console.log(profile.dataF);
    profile.exposeUsersWhoRated();



})




//
$(document).on('click',".icon-wrapper[searchId='target']",async function(e){
        // $(e.target.parentElement.parentElement.parentElement.parentElement).submit();
        let songId = $(e.target.parentElement.parentElement.parentElement.previousElementSibling).val();
        let playlistIdToUpdate = $(e.target.parentElement.parentElement.parentElement.previousElementSibling.previousElementSibling.previousElementSibling).val();

        let playlist = {
            playlistName: $(e.target.parentElement.parentElement.parentElement.previousElementSibling.previousElementSibling).val(),
            id: playlistIdToUpdate
        }
        let fetchOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': $(e.target.parentElement.parentElement.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling).val()
            },
            body: JSON.stringify(playlist)
        }
        let deletedSong = await fetch(`/profile/playlist/${songId}/delete/${profile.username}`, fetchOptions);

        // the following reloads the playlist with current songs
        profile.playlistId = playlistIdToUpdate;
        profile.dataF = await fetch(`/profile/playlist/${profile.playlistId}/${profile.username}`).then(res => res.json());
        profile.playlistUpdate();
        $('#userContainerName').html('');
        profile.exposeUsersWhoRated();

})
$(document).on('click',".icon-wrapper[searchId='not-target']",async function(e){
    $(e.target.parentElement.parentElement.parentElement.parentElement).submit();
})


// Displays Trophy Icons on Profile Page Top 3

if($(".profile-rank span").text() === "1")
    $(".profile-rank").append('<i class="bi goldTrophy bi-trophy-fill"></i>');

if($(".profile-rank span").text() === "2")
    $(".profile-rank").append('<i class= "bi silverTrophy bi-trophy-fill"></i>');

if($(".profile-rank span").text() === "3")
    $(".profile-rank").append('<i class="bi bronzeTrophy bi-trophy-fill"></i>');

