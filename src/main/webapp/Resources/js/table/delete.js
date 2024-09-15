import { deleteRegister } from "../CallBackend/deleteRegisterById.js";
import { isStatusOk } from "../CallBackend/verifyStatus.js";
import { getDBIdFromHighLightRow } from "./Highlight.js";
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js";
import { leftScreenToastSuccess } from "../alert/success/toast.js";
import { consoleErrorWithoutStopExecution } from "../alert/Error/console.js";
const showToastMessage = function (isSuccess, title, description) {
    const toast = new toastMessage(title, description);
    if (isSuccess) {
        leftScreenToastSuccess(toast);
    }
    else {
        leftScreenToastError(toast);
    }
};
const removeRegisterFromDB = async function (id) {
    try {
        const response = await deleteRegister(id);
        const wasDeleted = isStatusOk(response);
        if (wasDeleted) {
            const message = "was remove success";
            showToastMessage(true, "", message);
            return true;
        }
        else {
            const errorMessage = "backend response with a error";
            const errorTitle = "couldn't remove register";
            showToastMessage(false, errorTitle, errorMessage);
            return false;
        }
    }
    catch (e) {
        const generalMessage = "was an error trying remove register the error is : \n";
        if (e instanceof Error)
            consoleErrorWithoutStopExecution(generalMessage + e.message);
        else {
            consoleErrorWithoutStopExecution(generalMessage);
        }
    }
};
const removeRegisterFromTable = function (register) {
    register.remove();
};
const removeHightlightRegister = async function () {
    const registerId = getDBIdFromHighLightRow();
    if (registerId === null) {
        const errorTitle = "couldn't remove register";
        const errorDescription = "register id wasn't find";
        showToastMessage(false, errorTitle, errorDescription);
        return;
    }
    // if register id is different to null so register exist on DOM
    const register = document.querySelector(".HighlightRow");
    const wasRemovedFromDB = await removeRegisterFromDB(registerId);
    if (wasRemovedFromDB)
        removeRegisterFromTable(register);
};
export { removeHightlightRegister };
