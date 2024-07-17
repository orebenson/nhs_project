const adminSearchUserFormConfig = {
    formElementId: 'admin-search-user-form',
    validationRules: [
        {
            fieldId: 'email',
            errorMessage: 'Enter patient email address',
            rules: (value) => value !== ''
        }
    ]
};

document.getElementById(adminSearchUserFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, adminSearchUserFormConfig);
});
