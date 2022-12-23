$(function () {
// When the user clicks on the button, open the modal
    $('.dnModal-button').on('click', function () {
        $('.dnModal, .dnModal-content').css('display', "block");
    });
// When the user clicks on <span> (x), close the modal
    $('.dnModal-close').on('click', function () {
        $('.dnModal, .dnModal-content').css('display', "none");
    });


// When the user clicks anywhere outside of the modal, close it
    const modal = document.getElementsByClassName('dnModal')[0];
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

// //When the User types into the search bar in the model, it will populate a list of music
    $('.modal-search').each(function(){
        $(this).find('input').keypress()(function(e){
            if(e.which == 10 || e.which == 13){
                this.form.submit();
            }
        });
        $(this).find('input[type=submit]').hide();
    })
})