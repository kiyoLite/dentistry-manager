import { buttonPushed, defaultTableSize, buttonPushedType } from "../main/shiftManager.js";
import { consoleErrorStopExecution, consoleErrorWithoutStopExecution } from "../alert/Error/console.js";
import { curFilter, getSearch } from "./filterRegister.js";
import { getRegisterShiftTable, FetchConfig } from "../CallBackend/getRegisters.js";
import { postDataToRows } from "./PostData.js";
import { toastMessage, leftScreenToastError } from "../alert/Error/toast.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
import { setContainerSize } from "../other/setContinerSize.js";
import { createRowsSqueleton } from "./CreateRow.js";
const getFetchConfigToPagination = function () {
    const referenceId = getRequireId(buttonPushed);
    const isNextPage = buttonPushed === buttonPushedType.next;
    const searchBy = getSearch();
    return new FetchConfig(defaultTableSize, referenceId, curFilter, searchBy, isNextPage);
};
const getPageData = async function (queryFilter) {
    let data = null;
    await getRegisterShiftTable(queryFilter)
        .then(response => response.json())
        .then(result => data = result)
        .catch(e => {
        consoleErrorStopExecution(e);
    });
    return data;
};
const getIds = function (registers) {
    const ids = registers.map((curRegister) => {
        const unformattedId = curRegister.getAttribute("data-Id");
        const id = parseInt(unformattedId);
        if (isNaN(id)) {
            const errorMessage = "couldn't get the register id";
            consoleErrorStopExecution(errorMessage);
        }
        return id;
    });
    return ids;
};
const getRequireId = function (idType) {
    const registersContiner = document.getElementById("ContainerRegisters");
    verifyDOMElementExisteOrError(registersContiner);
    const registers = Array.from(registersContiner.rows);
    return idType === buttonPushedType.next
        ? Math.max(...getIds(registers))
        : Math.min(...getIds(registers));
};
const throttle = function () {
    let isAvalibleToFire = true;
    return async function (queryFilter) {
        if (isAvalibleToFire) {
            isAvalibleToFire = false;
            const data = await getPageData(queryFilter);
            const registersContainer = document.getElementById("ContainerRegisters");
            verifyDOMElementExisteOrError(registersContainer);
            setContainerSize(Object.keys(data).length, registersContainer, createRowsSqueleton);
            postDataToRows(data, registersContainer);
            isAvalibleToFire = true;
        }
    };
};
const GeneratePageThrotthle = throttle();
const tryGeneratePage = async function (config = getFetchConfigToPagination()) {
    try {
        await GeneratePageThrotthle(config);
    }
    catch (e) {
        const error = e;
        consoleErrorWithoutStopExecution(error.message);
        const userErrorDescription = "couldn't generate new page";
        const userErrorTitle = "Error";
        const toast = new toastMessage(userErrorTitle, userErrorDescription);
        leftScreenToastError(toast);
    }
};
export { tryGeneratePage };
