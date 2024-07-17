const adminRegisterFormConfig = {
    formElementId: 'admin-register-form',
    validationRules: [
        {
            fieldId: 'email',
            errorMessage: 'Enter your email address',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'firstname',
            errorMessage: 'Enter your first name',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'lastname',
            errorMessage: 'Enter your last name',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'password',
            errorMessage: 'Enter your password',
            rules: (value) => value !== '' && value.length > 3
        },
        {
            fieldId: 'address1',
            errorMessage: 'Enter your address',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'city',
            errorMessage: 'Enter your city',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'postcode',
            errorMessage: 'Enter your postcode',
            rules: (value) => value !== ''
        }
    ]
};

document.getElementById(adminRegisterFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, adminRegisterFormConfig);
});
