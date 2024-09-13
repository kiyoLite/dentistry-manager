import { highlightSingleRow, getDBIdFromHighLightRow } from "../table/Highlight.js";
import { removeHightlightRegisterFromTableAndDB } from "../table/delete.js";
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js";
import { changePlaceHolderByFilter } from "../table/filterRegister.js";
import { tryGeneratePage } from "../table/getPageContent.js";
import { FetchConfig, filterType } from "../CallBackend/getRegisters.js";
import { showMoreInfo } from "../table/moreInfoRegister.js";



// =====others=====
let defaultTableSize = 6;
enum buttonPushedType {
    next, previous
}
let buttonPushed = buttonPushedType.next;



// ================

const ContainerRegisters = document.getElementById("ContainerRegisters");
const nextPageButton = document.getElementById("nextPage");
const previousPageButton = document.getElementById("PreviousPage");
const garbageButton = document.getElementById("DeleteRegisterButton");
const updateRegisterButton = document.getElementById("updateRegisterButton");
// const createRegisterButton = document.getElementById("CreateRegisterButton");
const ContinerFilterSearch = document.getElementById("FilterBy");


ContainerRegisters?.addEventListener("click", highlightSingleRow);
nextPageButton?.addEventListener("click", () => {
    buttonPushed = buttonPushedType.next;
    tryGeneratePage();
});
previousPageButton?.addEventListener("click", () => {
    buttonPushed = buttonPushedType.previous;
    tryGeneratePage();
});

garbageButton?.addEventListener("click", removeHightlightRegisterFromTableAndDB);
ContinerFilterSearch?.addEventListener("change", changePlaceHolderByFilter)


updateRegisterButton?.addEventListener("click", (e) => {
    e.preventDefault();
    let idForUpdateRegister = getDBIdFromHighLightRow();
    if (idForUpdateRegister !== null) {
        sessionStorage.setItem("updateRegisterId", idForUpdateRegister.toString());
        window.location.href = "http://localhost:8080/DentistryManager/shiftManager.html"
    }
    else {
        const errorTitle = "you need first select a register"
        const errorDescription = "click on a register first please"
        const toastInfo = new toastMessage(errorTitle, errorDescription);
        leftScreenToastError(toastInfo)
    }
})
//@ts-ignore
const initializeTable = function () {
    const config = new FetchConfig(defaultTableSize, 0, filterType.default, "", true);
    tryGeneratePage(config)
}

ContainerRegisters?.addEventListener("click", showMoreInfo);
 initializeTable();


export { defaultTableSize, buttonPushed, buttonPushedType }