const zoomCarousel = {
    count: 1,
    max: 4,
    interval: 3250
}

const intervalCarousel = setInterval(function () {
    if (zoomCarousel.count >= zoomCarousel.max) {
        zoomCarousel.count = 1;
        $(`#page${zoomCarousel.count}cb`).click();
    } else {
        zoomCarousel.count = zoomCarousel.count + 1;
        $(`#page${zoomCarousel.count}cb`).click();
    }
}, zoomCarousel.interval);