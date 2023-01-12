// IIFE
potifyAPIController = (async function() {
    // check keys.js.
    const clientId = $('#spotify-keys').attr('data-client-id');
    const clientSecret = $('#spotify-keys').attr('data-client-secret');

    // Token is good for 1 hour, but each call should get a new one.
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

    // The following is the function that times the API call on key up in the search bar
    let bearerToken = await getToken();
    let usersSearch;
    $('.modal-search-bar').on('keyup', function() {
        let searchValue = $(this).val();
        if (usersSearch === searchValue) {
            usersSearch = searchValue;
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

    // The following is the actual API call function
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
        $('.modal-fill').html('');
        console.log(data);

        // The following loop populates the search results and creates the event handler for adding new songs
        let track;
        let artist;
        let image;
        let id;
        for (let i = 0; i < 5; i++){
            track = await data.tracks.items[i].name;//grabbing the name of the track
            artist = await data.tracks.items[i].artists[0].name;//grabbing the name of the Artist
            image = await data.tracks.items[i].album.images[data.tracks.items[i].album.images.length - 1].url;
            id = await data.tracks.items[i].id;
            // return track;

//Appending Image, Artist, and Track Name to the Modal Search
            $('.modal-fill').append(
                `<div class="searchline border">
                    <img src="${image}" alt="fail">
                    <span>
                        <p>${artist}</p>
                        <p>${track}</p>
                    </span>
                    <button class="addButton" id="addSong${id}" data-loop-id="${i}">Add song to playlist</button>
                </div>`);
            $(document).off('click', `#addSong${id}`);
            $(document).on('click', `#addSong${id}`, async function(e){
                e.preventDefault();
                console.log(e)
                let song = {
                    title: $(e.target.previousElementSibling.children[1]).text(),
                    image: $(e.target.previousElementSibling.previousElementSibling).attr('src'),

                    artist: {
                        artistName: $(e.target.previousElementSibling.children[0]).text()
                    }
                }
                console.log(song);
                let fetchOptions = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content")
                    },
                    body: JSON.stringify(song)

                }
                console.log(`/song/playlist/${$('#playlist-name').attr("plId")}`);
                let addedSong = await fetch(`/song/playlist/${$('#playlist-name').attr("plId")}`, fetchOptions)

                $(e.target.parentElement).html('');
            }).on("keypress", '.modal-search-bar', function (event) {
                if (event.keyCode === 13 || event.which === 13) {
                    event.preventDefault();
                }
            })

        }
    }



})();