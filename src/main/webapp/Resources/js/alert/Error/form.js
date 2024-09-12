import { ErrorInput, InputErrorType } from "./input.js";
import { errorTextArea, errorTextAreaType } from "./textArea.js";
const errorFunctionAccordingTagName = {
    input: ErrorInput,
    textarea: errorTextArea
};
const errorTypeAccordingTagName = {
    input: InputErrorType.empty,
    textarea: errorTextAreaType.empty
};
const errorElementsInForm = function (form) {
    const formId = form.id;
    const query = `#${formId} *:required:invalid`;
    const invalidElements = Array.from(document.querySelectorAll(query));
    for (let invalidElement of invalidElements) {
        const ElementName = invalidElement.tagName.toLowerCase();
        //all function must have the same arguments 
        const errorFunction = errorFunctionAccordingTagName[ElementName];
        const errorType = errorTypeAccordingTagName[ElementName];
        errorFunction(invalidElement, errorType);
    }
};
export { errorElementsInForm };
