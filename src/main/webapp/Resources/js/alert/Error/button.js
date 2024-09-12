var ButtonErrorType;
(function (ButtonErrorType) {
    ButtonErrorType[ButtonErrorType["noFind"] = 0] = "noFind";
    ButtonErrorType[ButtonErrorType["General"] = 1] = "General";
})(ButtonErrorType || (ButtonErrorType = {}));
const errorMessages = new Map();
errorMessages.set(ButtonErrorType.noFind, "don't exist");
errorMessages.set(ButtonErrorType.General, "was an error");
const errorButton = function (button, errorType) {
    const errorMessage = errorMessages.get(errorType);
    const originalContentButton = button.textContent ?? "";
    let secondsAnimationDuration = 3000;
    button.textContent = errorMessage;
    button.classList.add("errorButton");
    setTimeout(() => {
        button.textContent = originalContentButton;
        button.classList.remove("errorButton");
    }, secondsAnimationDuration);
};
export { errorButton, ButtonErrorType };
