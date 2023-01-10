const ctx = document.getElementById('myChart');

new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['6th', '5th', '4th', '3rd', '2nd', 'most recent'],
        datasets: [{
            label: 'last 6 ratings for Playlist',
            data: [12, 19, 95, 5, 2, 3],
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