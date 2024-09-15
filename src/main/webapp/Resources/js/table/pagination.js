import { tryGenerateNextPage, tryGeneratePreviousPage } from "../main/shiftManager.js";
import { leftScreenToastWarning } from "../alert/warning/toast.js";
import { toastMessage } from "../alert/Error/toast.js";
import { getSearch, getFilter } from "./filterRegister.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
const adviseUserPaginationDisabled = function () {
    const description = "make a search (click magnifying glass) with new filter first";
    const adviseMessage = new toastMessage("", description);
    leftScreenToastWarning(adviseMessage);
};
const removePagination = function () {
    const nextPageButton = document.getElementById("nextPage");
    const previousPageButton = document.getElementById("PreviousPage");
    nextPageButton?.removeEventListener("click", tryGenerateNextPage);
    previousPageButton?.removeEventListener("click", tryGeneratePreviousPage);
    nextPageButton?.addEventListener("click", adviseUserPaginationDisabled);
    previousPageButton?.addEventListener("click", adviseUserPaginationDisabled);
};
const recoverPagination = function () {
    const nextPageButton = document.getElementById("nextPage");
    const previousPageButton = document.getElementById("PreviousPage");
    nextPageButton?.addEventListener("click", tryGenerateNextPage);
    previousPageButton?.addEventListener("click", tryGeneratePreviousPage);
    nextPageButton?.removeEventListener("click", adviseUserPaginationDisabled);
    previousPageButton?.removeEventListener("click", adviseUserPaginationDisabled);
};
const blockPagination = function () {
    const filterOptions = document.getElementById("FilterBy");
    const searchBar = document.getElementById("SearchRegisterInput");
    verifyDOMElementExisteOrError(filterOptions);
    verifyDOMElementExisteOrError(searchBar);
    const lastFilter = filterOptions.getAttribute("data-lastFilter");
    const lastSearch = searchBar.getAttribute("data-lastSearch");
    if (lastSearch === getSearch() && lastFilter === getFilter()) {
        recoverPagination();
    }
    else {
        removePagination();
    }
};
export { blockPagination, recoverPagination };
