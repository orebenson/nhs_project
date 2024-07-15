
// dynamic form validator - only works for forms with just text input fields

function validateForm(event, config) {
    const formElement = document.getElementById(config.formElementId);
    if (!formElement) {
        console.error(`Form element with id '${config.formElementId}' not found.`);
        return false;
    }

    resetErrors();

    let isValid = true;

    config.validationRules.forEach(rule => {
        const fieldElement = document.getElementById(rule.fieldId);
        if (!fieldElement) {
            console.error(`Field element with id '${rule.fieldId}' not found.`);
            return;
        }

        const fieldValue = fieldElement.value.trim();
        if (!rule.rules(fieldValue)) {
            isValid = false;
            fieldElement.classList.add('nhsuk-input--error');
            fieldElement.parentElement.classList.add('nhsuk-form-group--error');
            createErrorMessage(fieldElement, rule.fieldId, rule.errorMessage);
            addToErrorSummary(rule.fieldId, rule.errorMessage);
        }
    });

    if (!isValid) {
        event.preventDefault();
        document.getElementById('error-summary-title').innerText = 'There is a problem';
        document.querySelector('.nhsuk-error-summary').classList.remove('nhsuk-u-visually-hidden');
    }

    return isValid;
}

function resetErrors() {
    const formGroups = document.querySelectorAll('.nhsuk-form-group--error');
    formGroups.forEach(group => {
        group.classList.remove('nhsuk-form-group--error');
    });

    const errorMessages = document.querySelectorAll('.nhsuk-error-message');
    errorMessages.forEach(message => {
        message.parentElement.removeChild(message);
    });

    const errorBoxes = document.querySelectorAll('.nhsuk-input--error');
    errorBoxes.forEach(box => {
        box.classList.remove('nhsuk-input--error');
    });

    const errorSummaryList = document.getElementById('error-summary-list');
    while (errorSummaryList.firstChild) {
        errorSummaryList.removeChild(errorSummaryList.firstChild);
    }
}

function createErrorMessage(inputElement, fieldId, errorMessage) {
    const errorSpan = document.createElement('span');
    errorSpan.classList.add('nhsuk-error-message');
    errorSpan.id = fieldId + '-error';
    errorSpan.innerText = errorMessage;
    inputElement.parentElement.appendChild(errorSpan);
}

function addToErrorSummary(fieldId, message) {
    const errorSummaryList = document.getElementById('error-summary-list');
    const listItem = document.createElement('li');
    const link = document.createElement('a');
    link.href = `#${fieldId}`;
    link.innerText = message;
    listItem.appendChild(link);
    errorSummaryList.appendChild(listItem);
}
