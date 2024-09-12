var InputErrorType;
(function (InputErrorType) {
    InputErrorType[InputErrorType["empty"] = 0] = "empty";
    InputErrorType[InputErrorType["invalidFormat"] = 1] = "invalidFormat";
})(InputErrorType || (InputErrorType = {}));
const errorMessages = new Map();
errorMessages.set(InputErrorType.empty, "missing field");
errorMessages.set(InputErrorType.invalidFormat, "set a valid format please");
const ErrorInput = function (input, ErrorType) {
    const errorMessage = errorMessages.get(ErrorType);
    const placeHolderBeforeErrorMessage = input.getAttribute("placeholder") ?? "";
    input.value = "";
    input.classList.add("errorInput");
    input.setAttribute("placeholder", errorMessage);
    setTimeout(() => {
        input.classList.remove("errorInput");
        input.setAttribute("placeholder", placeHolderBeforeErrorMessage);
    }, 4000);
};
export { ErrorInput, InputErrorType };
