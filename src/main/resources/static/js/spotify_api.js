// alert("yo!!");

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
        // let usersSearch = "supermassive";




    let usersSearch;
    $('.modal-search-bar').on('keyup', function(e) {
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




    const getSearch = async(bearer, search = usersSearch) => {
        const response = await fetch(`https://api.spotify.com/v1/search?type=track&q=${search}`,{
            method: 'GET',
            headers: {
                'Authorization' : `Bearer ${bearer}`,
                'Content-Type' : 'application/json'
            }
        });

        const data = await response.json()
        console.log(data);
        const track = await data.tracks.items[0].name;//grabbing the name of the track
        console.log(track)
        const artist = await data.tracks.items[0].artists[0].name//grabbing the name of the Artist
        console.log(artist);
        return track;

    }



})();



