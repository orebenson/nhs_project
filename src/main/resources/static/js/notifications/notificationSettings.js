$(document).ready(function() {
    $('#notification-settings-form').on('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function(response) {
                $('#notification-message').html('<p>Settings saved and notifications sent successfully!</p>');
            },
            error: function(error) {
                $('#notification-message').html('<p>There was an error sending the notifications. Please try again.</p>');
            }
        });
    });
});
