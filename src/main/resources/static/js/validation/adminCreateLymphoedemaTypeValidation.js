const adminAddLymphoedemaTypeConfig = {
    formElementId: 'admin-create-lymphoedema-type-form',
    validationRules: [
        {
            fieldId: 'name',
            errorMessage: 'Enter the lymphoedema type name',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'description',
            errorMessage: 'Enter the lymphoedema typen description',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'measurement-0-name',
            errorMessage: 'Enter at least one measurement name',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'measurement-0-description',
            errorMessage: 'Enter at least one measurement description',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'measurement-0-unit',
            errorMessage: 'Enter at least one measurement unit',
            rules: (value) => value !== ''
        }
    ]
};

document.getElementById(adminAddLymphoedemaTypeConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, adminAddLymphoedemaTypeConfig);
});
