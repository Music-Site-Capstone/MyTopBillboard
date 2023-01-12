// alert("Welcom to the Homepage Jquery is Working!");
//homepage search bar to search for profiles
$(document).ready(function () {
    $("#submit").click(function (e) {
        e.preventDefault();
        let formValue = $("#code").val();
        window.location.replace('/profile/' + formValue);
    });
})

//
// $(function () {
//     var info = [
//         "ActionScript",
//         "AppleScript",
//         "Asp",
//         "BASIC",
//         "C",
//         "C++",
//         "Clojure",
//         "COBOL",
//         "ColdFusion",
//         "Erlang",
//         "Fortran",
//         "Groovy",
//         "Haskell",
//         "Java",
//         "JavaScript",
//         "Lisp",
//         "Perl",
//         "PHP",
//         "Python",
//         "Ruby",
//         "Scala",
//         "Scheme"
//     ];
//
//     $("#code").autocomplete({
//         source: info
//     });
//
// });
