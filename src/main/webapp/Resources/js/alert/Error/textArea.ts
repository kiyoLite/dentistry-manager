enum errorTextAreaType {
    empty
}

const errorMessages = new Map();
errorMessages.set(errorTextAreaType.empty, "missing field");


const errorTextArea = function (textArea: HTMLTextAreaElement, errorType: errorTextAreaType) {
    textArea.value = ""
    const errorMessage = errorMessages.get(errorType);
    textArea.classList.add("errorTextArea");
    textArea.setAttribute("placeholder", errorMessage);
    setTimeout(() => {
        textArea.removeAttribute("placeholder");
        textArea.classList.remove("errorTextArea")
    }, 4000);
}

export { errorTextArea, errorTextAreaType }