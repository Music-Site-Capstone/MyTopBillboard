$('#login-button').click(function(){
    $('#login-button').fadeOut("slow",function(){
        $("#container").fadeIn();
        gsap.from("#container", .4, { scale: 0, ease:Sine.easeInOut});
        gsap.to("#container", .4, { scale: 1, ease:Sine.easeInOut});
    });
});

$(".close-btn").click(function(){
    gsap.from("#container", .4, { scale: 1, ease:Sine.easeInOut});
    gsap.to("#container", .4, { left:"0px", scale: 0, ease:Sine.easeInOut});
    $("#container, #forgotten-container").fadeOut(800, function(){
        $("#login-button").fadeIn(800);
    });
});

/* Forgotten Password */
$('#forgotten').click(function(){
    $("#container").fadeOut(function(){
        $("#forgotten-container").fadeIn();
    });
});