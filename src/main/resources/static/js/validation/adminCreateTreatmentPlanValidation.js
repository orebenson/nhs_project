const adminCreateTreatmentPlanFormConfig = {
    formElementId: 'admin-create-plan-form',
    validationRules: [
        {
            fieldId: 'name',
            errorMessage: 'Enter the treatment plan name',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'description',
            errorMessage: 'Enter the treatment plan description',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'exercise-0-name',
            errorMessage: 'Enter at least one exercise name',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'exercise-0-description',
            errorMessage: 'Enter at least one exercise description',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'exercise-0-video-link',
            errorMessage: 'Enter at least one exercise video',
            rules: (value) => value !== ''
        }
    ]
};

document.getElementById(adminCreateTreatmentPlanFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, adminCreateTreatmentPlanFormConfig);
});
