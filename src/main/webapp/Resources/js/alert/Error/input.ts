enum InputErrorType {
    empty,
    invalidFormat
}
const errorMessages = new Map();
errorMessages.set(InputErrorType.empty, "missing field");
errorMessages.set(InputErrorType.invalidFormat, "set a valid format please");


const ErrorInput = function (input: HTMLInputElement, ErrorType: InputErrorType) {
    const errorMessage: string = errorMessages.get(ErrorType);
    const placeHolderBeforeErrorMessage = input.getAttribute("placeholder") ?? "";
    input.value = "";
    input.classList.add("errorInput");
    input.setAttribute("placeholder", errorMessage);
    setTimeout(() => {
        input.classList.remove("errorInput");
        input.setAttribute("placeholder", placeHolderBeforeErrorMessage);
    }, 4000)
}




export { ErrorInput, InputErrorType }
