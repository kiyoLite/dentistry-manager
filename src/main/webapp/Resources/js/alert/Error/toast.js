class toastMessage {
    constructor(title, description) {
        this.title = title;
        this.description = description;
    }
}
const createToastError = function (toastErrorMessage) {
    const toastError = document.createElement("div");
    toastError.innerHTML = `
        <div class="ContainerErrorImage">
            <img src="../images/error.svg" alt="" class="ErrorImage">
        </div>
        <div class="ContaienrErrorText">
            <p class="Title">${toastErrorMessage.title}</p>
            <p class="description">${toastErrorMessage.description.toLowerCase()}</p>
        </div>
        `;
    toastError.classList.add("ToastErrorContainer");
    return toastError;
};
const removeToast = function (ToastError, delay) {
    setTimeout(() => {
        ToastError.remove();
    }, delay);
};
const leftScreenToastError = function (toastErrorMessage) {
    const header = document.getElementById("ContainerHedaer");
    const headerHeight = header?.offsetHeight ?? 0;
    const toastError = createToastError(toastErrorMessage);
    const Axes = header?.getBoundingClientRect();
    toastError.style.top = (Axes?.top ?? 0 + headerHeight) + "px";
    toastError.style.left = Axes?.left + "px";
    document.body.insertAdjacentElement("afterbegin", toastError);
    const toastErrorSecondDuration = 5000;
    removeToast(toastError, toastErrorSecondDuration);
};
const middleScreenToastError = function (toastErrorMessage) {
    const toastError = createToastError(toastErrorMessage);
    toastError.style.top = "50%";
    toastError.style.left = "50%";
    toastError.style.transform = " translateX(-50%) translateY(-50%)";
    document.body.insertAdjacentElement("afterbegin", toastError);
    const toastSecondsDuration = 3000;
    removeToast(toastError, toastSecondsDuration);
    document.body.insertAdjacentElement("afterbegin", toastError);
};
export { leftScreenToastError, middleScreenToastError, toastMessage, removeToast };
