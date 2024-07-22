const diaryEntryFormConfig = {
    formElementId: 'diary-entry-form',
    validationRules: [
        {
            fieldId: 'weight',
            errorMessage: 'Enter your weight',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'wellnessScore',
            errorMessage: 'Enter your wellness score',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'qualityOfLifeScore',
            errorMessage: 'Enter your quality of life score',
            rules: (value) => value !== ''
        },
        {
            fieldId: 'cellulitisDetails',
            errorMessage: 'Entry must be less than 200 characters',
            rules: (value) => value.length < 201
        },
        {
            fieldId: 'mobilityDetails',
            errorMessage: 'Entry must be less than 200 characters',
            rules: (value) => value.length < 201
        },
        {
            fieldId: 'discomfortDetails',
            errorMessage: 'Entry must be less than 200 characters',
            rules: (value) => value.length < 201
        }
    ]
};


document.getElementById(diaryEntryFormConfig.formElementId).addEventListener('submit', function(event) {
    validateForm(event, diaryEntryFormConfig);
});
