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
        console.log(data.access_token);
        return data.access_token;

        }

       let bearerToken = await getToken();


    const getSearch = async(bearer) => {
        const response = await fetch(' https://api.spotify.com/v1/search?type=track&q=supermassiv',{
            method: 'GET',
            headers: {
                'Authorization' : `Bearer ${bearer}`,
                'Content-Type' : 'application/json'
            }
        });

        const data = await response.json()
        console.log(data);
        // return data.access_token;

    }

    await getSearch(bearerToken);

})();



