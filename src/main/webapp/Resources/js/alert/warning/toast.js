import { removeToast } from "../Error/toast.js";
const createToastWarning = function (toastMessage) {
    const toast = document.createElement("div");
    toast.innerHTML = `
        <div class="ContainerImage">
            <img src="Resources/images/warningIcon.svg" alt="" class="Image">
        </div>
        <div class="ContaienrText">
            <p class="Title">warning</p>
            <p class="Description">${toastMessage.description}</p>
        </div>
        `;
    toast.classList.add("WarningToastContainer");
    return toast;
};
const leftScreenToastWarning = function (toastMessage) {
    const header = document.getElementById("ContainerHedaer");
    const headerHeight = header?.offsetHeight ?? 0;
    const toast = createToastWarning(toastMessage);
    const Axes = header?.getBoundingClientRect();
    toast.style.top = (Axes?.top ?? 0 + headerHeight) + "px";
    toast.style.left = Axes?.left + "px";
    document.body.insertAdjacentElement("afterbegin", toast);
    const toastSecondDuration = 5000;
    removeToast(toast, toastSecondDuration);
};
export { leftScreenToastWarning };
