$(document).ready(function() {
    $('#notification-form').on('submit', function(e) {
        e.preventDefault(); // Prevent the default form submission

        // Create a FormData object if necessary
        let formData = new FormData(this);

        $.ajax({
            type: 'POST',
            url: $('#notification-form').attr('action'),
            data: $(this).serialize(),
            success: function() {
                // Extract userId dynamically from the body data attribute
                var userId = $('body').data('userid');

                // Construct the dynamic URL with the userId
                window.location.href = "/admin/search/" + userId + "#details";
            },
            error: function(response) {
                console.log('Error sending notifications: ', response);
            }
        });

    });
});
