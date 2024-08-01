const consentFormConfig = {
    formElementId: 'consent-form',
    validationRules: [
        {}
    ]
};

document.getElementById(consentFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, consentFormConfig);
});
