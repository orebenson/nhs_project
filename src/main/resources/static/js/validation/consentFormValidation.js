const consentFormConfig = {
    formElementId: 'consent-form',
    validationRules: [
        {
            fieldId: 'photo-consent',
            errorMessage: 'Photo consent must be accepted to continue',
            rules: (value) => value !== false
        },
        {
            fieldId: 'info-consent',
            errorMessage: 'Information consent must be accepted to continue',
            rules: (value) => value !== false
        }
    ]
};

document.getElementById(consentFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, consentFormConfig);
});
