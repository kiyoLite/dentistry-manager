import { filterType } from "../CallBackend/getRegisters.js";
const containerFilter = document.getElementById("FilterBy");
const searchRegisterInput = document.getElementById("SearchRegisterInput");
let curFilter = filterType.default;
const PlaholderByFilter = {
    default: "",
    price: "0-100",
    scheduling: "MM-DD-YYYY"
};
const getFilter = function () {
    const filter = containerFilter?.value;
    return filter;
};
const getSearch = function () {
    const search = searchRegisterInput?.value ?? "";
    return search;
};
const changePlaceHolderByFilter = function () {
    const filter = getFilter();
    // if the input doesnt have a custom place holder is because you should put
    // the name
    const PlaceHolder = PlaholderByFilter[filter] || "NAME";
    searchRegisterInput?.setAttribute("placeholder", PlaceHolder);
};
containerFilter?.addEventListener("change", () => {
    const ContainerFilterValue = getFilter();
    curFilter = ContainerFilterValue !== undefined
        ? ContainerFilterValue
        : filterType.default;
});
export { getSearch, getFilter, curFilter, changePlaceHolderByFilter };
