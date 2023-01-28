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

$(document).ready(function (){
    $("input[type=submit]").prop("disabled", true);
})
$('.input3').on('keyup',function(){
    if (($('#username').val().length === 0)
        || ($('#password').val().length === 0)){
        $("input[type=submit]").prop("disabled", true);
    } else{
        $("input[type=submit]").prop("disabled", false);
    }
}).on("keypress", function (event) {
    if (event.keyCode === 13 || event.which === 13) {
        event.preventDefault();
    }
});
