import { deleteRegister } from "../CallBackend/deleteRegisterById.js";
import { isStatusOk } from "../CallBackend/verifyStatus.js";
import { getHightlightRow } from "./Highlight.js";
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js";
import { consoleErrorStopExecution } from "../alert/Error/console.js";


enum removeRegisterErrorType {
    getRegister, getId, DB
}

const errorMessage = new Map();
errorMessage.set(removeRegisterErrorType.getId, `could get the register id `);
errorMessage.set(removeRegisterErrorType.getRegister, 'doesnt exist that  element on DOM');
errorMessage.set(removeRegisterErrorType.DB, "backend couldn't remove register");

const showError = function (errorType: removeRegisterErrorType) {
    const description = errorMessage.get(errorType);
    const title = "error removing register"
    const message = new toastMessage(title, description);
    leftScreenToastError(message);

    const consoleMessage = "was a error trying remove a register"
    consoleErrorStopExecution(consoleMessage);
}
const removeHighlightRegisterFromDB = async function (id: number) {
    let wasRemoveFromDB = false;
    await deleteRegister(id).then((response: Response) => wasRemoveFromDB = isStatusOk(response));
    return wasRemoveFromDB;
}


const removeHighlightRegisterFromTable = function (register: HTMLTableRowElement) {
    register.remove();
}

const removeHightlightRegisterFromTableAndDB = async function () {
    const register = getHightlightRow() as HTMLTableRowElement | null;
    if (register === null) showError(removeRegisterErrorType.getRegister);
    const attributeDBIdName = "data-Id";
    const unformattedId = register!.getAttribute(attributeDBIdName);
    if (attributeDBIdName === null) showError(removeRegisterErrorType.getId);
    const formattedId = parseInt(unformattedId!);
    const wasRemovedFromDB = await removeHighlightRegisterFromDB(formattedId);
    if (wasRemovedFromDB) removeHighlightRegisterFromTable(register!);

    else {
        showError(removeRegisterErrorType.DB);
    }
}
export { removeHightlightRegisterFromTableAndDB }
