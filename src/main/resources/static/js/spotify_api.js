
SpotifyAPIController = (async function() {
    const clientId = SPOTIFY_CLIENT_ID;
    const clientSecret = SPOTIFY_CLIENT_SECRET;

    const getToken = async() => {
        const response = await fetch('https://accounts.spotify.com/api/token',{
            method: 'POST',
            headers: {
                'Authorization' : 'Basic ' + btoa(clientId + ':' + clientSecret),
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            body: 'grant_type=client_credentials'
        });

        const data = await response.json()
        console.log(data);
        console.log(data.access_token);
        return data.access_token;

        }

       let bearerToken = await getToken();





    let usersSearch;
    $('.modal-search-bar').on('keyup', function() {
        let searchValue = $(this).val();
        if (usersSearch === searchValue) {
            console.log('good');
        } else {
            setTimeout(async function () {
                let timedSearch = $('.modal-search-bar').val();
                if (searchValue === timedSearch) {
                    await getSearch(bearerToken, timedSearch);
                } else {
                    usersSearch = timedSearch;
                }
            }, 300);
        }
    })


  //   curl --request GET \
  // --url https://api.spotify.com/v1/recommendations/available-genre-seeds \
  //       --header 'Authorization: ' \
  // --header 'Content-Type: application/json'


  //   curl --request GET \
  // --url https://api.spotify.com/v1/artists/id \
  //       --header 'Authorization: ' \
  // --header 'Content-Type: application/json'

    let artistID;
    const getSearch = async(bearer, search = usersSearch, artistID) => {
        const response = await fetch(`https://api.spotify.com/v1/search?type=track&q=${search}`,{
            method: 'GET',
            headers: {
                'Authorization' : `Bearer ${bearer}`,
                'Content-Type' : 'application/json'
            }
        });
        const data = await response.json()
       artistID = data.tracks.items[0].artists[0].id;
        console.log(artistID);

        const genreResponse = await fetch(`https://api.spotify.com/v1/artists/${artistID}`,{
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${bearer}`,
                'Content-Type': 'application/json'
            }
        });

        $('.modal-fill').html('');

        const artistData = await genreResponse.json();
        console.log(artistData);
        let genres = artistData.genres.toString();
        console.log(genres);
        console.log(data);

        function addGenres(){

        }


        let track;
        let artist;
        let image;
        for (let i = 0; i < 5; i++){
            track = await data.tracks.items[i].name;//grabbing the name of the track
            artist = await data.tracks.items[i].artists[0].name;//grabbing the name of the Artist
            image = await data.tracks.items[i].album.images[data.tracks.items[i].album.images.length - 1].url;
            // return track;

            $('.modal-fill').append(`<div class="searchline"><img src="${image}" alt="fail"><p>${artist} - ${track}</p>
            <form th:action="@{profile/pageOwner}" th:method="POST" th:object="${artist}" th:object="${genres}">
            <button class="addButton" id="addGenres">Add song to playlist</button>
            </form>
            </div>`);
        }
    }



})();