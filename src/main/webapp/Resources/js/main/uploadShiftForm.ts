import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
import { fillUpdateRegisterForm } from "../form/fillForm.js";
import { getFormDataAsObj } from "../form/getData.js";
import { isFormContentCorrectOrWarning } from "../verify/form.js";
import { sendDataUpdateRegister } from "../CallBackend/UpdateRegister.js";
import { sendDataCreateRegister } from "../CallBackend/CreateRegister.js";
import { isStatusOk } from "../CallBackend/verifyStatus.js";
import { middleScreenToastSuccess } from "../alert/success/toast.js";
import { middleScreenToastError, toastMessage } from "../alert/Error/toast.js";
//@ts-ignore
import { FillSelectElementWithDentists } from "../form/postAllDentist.js";

//when enter to "form page" it's must create or update register using form data
//if the variable is different to null we need register id from DB for update
const registerId = sessionStorage.getItem("updateRegisterId");
const isUpdateMode = registerId !== null;




const changePageTitle = function () {
    const pageTitle = document.querySelector("h1");
    verifyDOMElementExisteOrError(pageTitle);
    const newTitle = "UPDATE REGISTER "
    pageTitle!.textContent = newTitle + "#" + registerId
}
if (isUpdateMode) {
    changePageTitle();
    const id = parseInt(registerId);
    fillUpdateRegisterForm(id);
}

const showResponseMessage = function (response: Response | null) {
    if (response === null || !isStatusOk(response)) {
        const errorTitle = "error upload form"
        const errorDescription = "backend couldn't save the data on DB"
        const errorMessage = new toastMessage(errorTitle, errorDescription);
        middleScreenToastError(errorMessage);
    }
    else {
        const successMessage = "content was saved on DB"
        const message = new toastMessage("", successMessage);
        middleScreenToastSuccess(message);
    }

};

const uploadData = async function () {
    if (!isFormContentCorrectOrWarning(form!)) return
    const data = getFormDataAsObj(form!);
    let backendResponse: Response | null = null;
    if (isUpdateMode) {
        const id = parseInt(registerId);
        await sendDataUpdateRegister(id, data)
            .then(response => backendResponse = response);
    }
    else {
        await sendDataCreateRegister(data)
            .then(response => backendResponse = response);
    }
    showResponseMessage(backendResponse);
}
const dentistField = document.querySelector(`select[name="dentist"]`) as HTMLSelectElement | null;

const form = document.getElementById("FormCreateShift") as HTMLFormElement | null;
verifyDOMElementExisteOrError(form);
verifyDOMElementExisteOrError(dentistField);
FillSelectElementWithDentists(dentistField!);
document.getElementById("SubmitFormButton")?.addEventListener("click", uploadData);




