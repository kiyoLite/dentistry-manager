import { errorElementsInForm } from "../alert/Error/form.js";
const isFormContentCorrect = function (form) {
    return form.checkValidity();
};
const isFormContentCorrectOrWarning = function (form) {
    const isFormCorrect = isFormContentCorrect(form);
    if (!isFormCorrect)
        errorElementsInForm(form);
    return isFormCorrect;
};
export { isFormContentCorrect, isFormContentCorrectOrWarning };
