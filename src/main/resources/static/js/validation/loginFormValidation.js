const loginFormConfig = {
    formElementId: 'login-form',
    validationRules: [
        {
            fieldId: 'email',
            errorMessage: 'Enter your email address',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'password',
            errorMessage: 'Enter your password',
            rules: (value) => value !== ''
        }
    ]
};


document.getElementById(loginFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, loginFormConfig);
});
