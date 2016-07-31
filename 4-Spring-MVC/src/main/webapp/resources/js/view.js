$(document).ready(function() {

    $("#submit").on("click", function(event) {
        event.preventDefault();
        event.stopPropagation();
        var email = $('userData');
        var userName = $('#name').val();
        var email = $('#email').val();
        var todo = $('#todo').val();
        var objectTobeSent = {
            "userName": userName,
            "email": email,
            "todo": todo
        };

        var createURL = "/todo";

        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: createURL,
            type: "POST",
            data: JSON.stringify(objectTobeSent),
            success: function(status, xhr) {
                window.location.href = "/api/users/"+email;
                console.log("todo created successfully");
            },
            error: function(request) {
                console.log("error creating todo");
            }
        });
    });


});