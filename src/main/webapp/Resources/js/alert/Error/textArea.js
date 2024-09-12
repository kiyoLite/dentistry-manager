var errorTextAreaType;
(function (errorTextAreaType) {
    errorTextAreaType[errorTextAreaType["empty"] = 0] = "empty";
})(errorTextAreaType || (errorTextAreaType = {}));
const errorMessages = new Map();
errorMessages.set(errorTextAreaType.empty, "missing field");
const errorTextArea = function (textArea, errorType) {
    textArea.value = "";
    const errorMessage = errorMessages.get(errorType);
    textArea.classList.add("errorTextArea");
    textArea.setAttribute("placeholder", errorMessage);
    setTimeout(() => {
        textArea.removeAttribute("placeholder");
        textArea.classList.remove("errorTextArea");
    }, 4000);
};
export { errorTextArea, errorTextAreaType };
