import { toastMessage, removeToast } from "../Error/toast.js";
const createToastSuccess = function (toastMessage: toastMessage) {
    const toast = document.createElement("div");
    toast.innerHTML = `
        <div class="ContainerImage">
            <img src="Resources/images/Check.svg" alt="" class="Image">
        </div>
        <div class="ContaienrText">
            <p class="Title">success</p>
            <p class="Description">${toastMessage.description}</p>
        </div>
        `;
    toast.classList.add("ToastSuccesssContainer");
    return toast;
}



const leftScreenToastSuccess = function (toastMessage: toastMessage) {
    const header = document.getElementById("ContainerHedaer");
    const headerHeight = header?.offsetHeight ?? 0;
    const toast = createToastSuccess(toastMessage);
    const Axes = header?.getBoundingClientRect();
    toast.style.top = (Axes?.top ?? 0 + headerHeight) + "px";
    toast.style.left = Axes?.left + "px";
    document.body.insertAdjacentElement("afterbegin", toast);
    const toastSecondDuration = 5000;
    removeToast(toast, toastSecondDuration);
}

const middleScreenToastSuccess = function (toastMessage: toastMessage) {

    const toast = createToastSuccess(toastMessage);
    toast.style.top = "50%";
    toast.style.left = "50%";
    toast.style.transform = " translateX(-50%) translateY(-50%)"
    document.body.insertAdjacentElement("afterbegin", toast);
    const toastSecondsDuration = 3000;
    removeToast(toast, toastSecondsDuration);
    document.body.insertAdjacentElement("afterbegin", toast);
}

export { leftScreenToastSuccess, middleScreenToastSuccess }
