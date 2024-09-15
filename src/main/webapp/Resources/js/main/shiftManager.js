import { highlightSingleRow, getDBIdFromHighLightRow } from "../table/Highlight.js";
import { removeHightlightRegisterFromTableAndDB } from "../table/delete.js";
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js";
import { changePlaceHolderByFilter, getFilter, getSearch } from "../table/filterRegister.js";
import { tryGeneratePage } from "../table/getPageContent.js";
import { FetchConfig, filterType } from "../CallBackend/getRegisters.js";
import { showMoreInfo } from "../table/moreInfoRegister.js";
import { blockPagination, recoverPagination } from "../table/pagination.js";
// =====others=====
let defaultTableSize = 6;
var buttonPushedType;
(function (buttonPushedType) {
    buttonPushedType[buttonPushedType["next"] = 0] = "next";
    buttonPushedType[buttonPushedType["previous"] = 1] = "previous";
})(buttonPushedType || (buttonPushedType = {}));
let buttonPushed = buttonPushedType.next;
// ================
const ContainerRegisters = document.getElementById("ContainerRegisters");
const nextPageButton = document.getElementById("nextPage");
const previousPageButton = document.getElementById("PreviousPage");
const garbageButton = document.getElementById("DeleteRegisterButton");
const updateRegisterButton = document.getElementById("updateRegisterButton");
const filterOptions = document.getElementById("FilterBy");
const searchButton = document.getElementById("searchButton");
const searchBar = document.getElementById("SearchRegisterInput");
//@ts-ignore
const initializeTable = function () {
    const config = new FetchConfig(defaultTableSize, 0, filterType.PREDETERMINED, "", true);
    tryGeneratePage(config);
};
ContainerRegisters?.addEventListener("click", showMoreInfo);
 initializeTable();
const tryGenerateNextPage =async  function () {
    buttonPushed = buttonPushedType.next;
    await tryGeneratePage();
};
const tryGeneratePreviousPage = async function () {
    buttonPushed = buttonPushedType.previous;
    await tryGeneratePage();
};
nextPageButton?.addEventListener("click", tryGenerateNextPage);
previousPageButton?.addEventListener("click", tryGeneratePreviousPage);
ContainerRegisters?.addEventListener("click", highlightSingleRow);
garbageButton?.addEventListener("click", removeHightlightRegisterFromTableAndDB);
filterOptions?.addEventListener("change", changePlaceHolderByFilter);
filterOptions?.addEventListener("change", blockPagination);
searchBar?.addEventListener("blur", blockPagination);
updateRegisterButton?.addEventListener("click", (e) => {
    e.preventDefault();
    let idForUpdateRegister = getDBIdFromHighLightRow();
    if (idForUpdateRegister !== null) {
        sessionStorage.setItem("updateRegisterId", idForUpdateRegister.toString());
        window.location.href = "https://www.semrush.com/blog/javascript-redirect/";
    }
    else {
        const errorTitle = "you need first select a register";
        const errorDescription = "click on a register first please";
        const toastInfo = new toastMessage(errorTitle, errorDescription);
        leftScreenToastError(toastInfo);
    }
});
const searchByNewFilter = function () {
    const curFilter = filterType[getFilter()];
    const curSearch = getSearch();
    const config = new FetchConfig(defaultTableSize, 0, curFilter, curSearch, true);
    recoverPagination();
    filterOptions.setAttribute("data-lastFilter", getFilter());
    searchBar.setAttribute("data-lastSearch", curSearch);
};
searchButton?.addEventListener("click", searchByNewFilter);
export { defaultTableSize, buttonPushed, buttonPushedType, tryGenerateNextPage, tryGeneratePreviousPage };
