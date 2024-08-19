function toggleInput(show) {
    const inputDiv = document.getElementById('numberInput');
    if (show) {
        inputDiv.style.display = 'block';
    } else {
        inputDiv.style.display = 'none';
    }
}


// document.addEventListener('DOMContentLoaded', function() {
//     //Function to display confirmation dialog
//     function confirmSubmission(event) {
//         if (!confirm("\nPlease check through your answers before submitting your Pre-Appointment Questionnaire.\n\nAre you sure you want to submit?")) {
//             event.preventDefault();
//         }
//     }
//
//     //Attaches the submit event listener to the form
//     const form = document.getElementById('preappointment-form');
//     form.addEventListener('submit', confirmSubmission);
// });
