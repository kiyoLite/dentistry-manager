enum ButtonErrorType {
    noFind,
    General
}

const errorMessages = new Map();
errorMessages.set(ButtonErrorType.noFind, "don't exist");
errorMessages.set(ButtonErrorType.General, "was an error");

const errorButton = function (button: HTMLButtonElement, errorType: ButtonErrorType) {
    const errorMessage: string = errorMessages.get(errorType);
    const originalContentButton = button.textContent ?? "";
    let secondsAnimationDuration = 3000;
    button.textContent = errorMessage;
    button.classList.add("errorButton");
    setTimeout(() => {
        button.textContent = originalContentButton;
        button.classList.remove("errorButton");
    }, secondsAnimationDuration)
}



export { errorButton, ButtonErrorType }
