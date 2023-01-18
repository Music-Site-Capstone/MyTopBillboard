const Spotify = {
    // check keys.js
    clientId: $('#spotify-keys').attr('data-client-id'),
    clientSecret: $('#spotify-keys').attr('data-client-secret'),
    bearerToken: "",
    usersSearch: "",
    // Token is good for 1 hour, but each call should get a new one.
    getToken: async () => {
        const response = await fetch('https://accounts.spotify.com/api/token',{
            method: 'POST',
            headers: {
                'Authorization' : 'Basic ' + btoa(Spotify.clientId + ':' + Spotify.clientSecret),
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            body: 'grant_type=client_credentials'
        });

        const data = await response.json()
        console.log(data);
        console.log(data.access_token);
        Spotify.bearerToken = data.access_token;
    },
    // The following is the actual API call function
    getSearch: async(bearer, search = Spotify.usersSearch) => {
        const response = await fetch(`https://api.spotify.com/v1/search?type=track&q=${search}`,{
            method: 'GET',
            headers: {
                'Authorization' : `Bearer ${bearer}`,
                'Content-Type' : 'application/json'
            }
        });
        const data = await response.json()
        $('.modal-fill').html('');
        console.log(data);

        // The following loop populates the search results and creates the event handler for adding new songs
        let track;
        let artist;
        let image;
        let id;
        let previewUrl;
        for (let i = 0; i < 20; i++){
            track = await data.tracks.items[i].name;//grabbing the name of the track
            artist = await data.tracks.items[i].artists[0].name;//grabbing the name of the Artist
            image = await data.tracks.items[i].album.images[data.tracks.items[i].album.images.length - 1].url;
            id = await data.tracks.items[i].id;
            previewUrl = await data.tracks.items[i].preview_url;

//Appending Image, Artist, and Track Name to the Modal Search
            $('.modal-fill').append(
                `<div class="searchline border" data-song-id="${id}">
                    <img class="search-image" src="${image}" alt="fail">
                    <span class="search-info">
                        <p class="search-artist-name">${artist}</p>
                        <p class="search-artist-track">${track}</p>
                    </span>
                    <button class="addButton" id="addSong${id}" data-loop-id="${i}">Add Song</button>          
                    <span hidden>${previewUrl}</span>
                </div>`);
            $(document).off('click', `#addSong${id}`);
            $(document).on('click', `#addSong${id}`, async function(e){
                e.preventDefault();
                console.log(e)
                let song = {
                    title: $(e.target.previousElementSibling.children[1]).text(),
                    image: $(e.target.previousElementSibling.previousElementSibling).attr('src'),
                    previewUrl: $(e.target.nextElementSibling).text(),
                    artist: {
                        artistName: $(e.target.previousElementSibling.children[0]).text()
                    }
                }
                console.log('test', song);
                let fetchOptions = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content")
                    },
                    body: JSON.stringify(song)
                }
                console.log(`/song/playlist/${$('#playlist-name').attr("plId")}`);
                let addedSong = await fetch(`/song/playlist/${$('#playlist-name').attr("plId")}`, fetchOptions);

                $(e.target.parentElement).html('');
            }).on("keypress", '.modal-search-bar', function (event) {
                if (event.keyCode === 13 || event.which === 13) {
                    event.preventDefault();
                }
            })
        }
    }
}

Spotify.getToken();

// The following is the function that times the API call on key up in the search bar
$('.modal-search-bar').on('keyup', function() {
    let searchValue = $(this).val();
    if (Spotify.usersSearch === searchValue) {
        Spotify.usersSearch = searchValue;
    } else {
        setTimeout(async function () {
            let timedSearch = $('.modal-search-bar').val();
            if (searchValue === timedSearch) {
                await Spotify.getSearch(Spotify.bearerToken, timedSearch);
            } else {
                Spotify.usersSearch = timedSearch;
            }
        }, 300);
    }
})

