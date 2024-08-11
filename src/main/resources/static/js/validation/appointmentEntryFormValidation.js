const appointmentEntryFormConfig = {
    formElementId: 'appointment-entry-form',
    validationRules: [
        {
            fieldId: 'date',
            errorMessage: 'Enter appointment date',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'type',
            errorMessage: 'Enter appointment type',
            rules: (value) => value !== '' && value.length < 400
        },
        {
            fieldId: 'description',
            errorMessage: 'Appointment description must be less than 400 characters',
            rules: (value) => value.length < 400
        }
    ]
};

document.getElementById(appointmentEntryFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, appointmentEntryFormConfig);
});
