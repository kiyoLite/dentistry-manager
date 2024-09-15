

const PlaholderByFilter: Record<string, string> = {
    default: "",
    price: "0-100",
    scheduling: "MM-DD-YYYY"
}
const getFilter = function () {
    const containerFilter = document.getElementById("FilterBy") as HTMLSelectElement | null;
    const filter = containerFilter?.value ?? "PREDETERMINED";
    return filter;
}

const getSearch = function () {
    const searchRegisterInput = document.getElementById("SearchRegisterInput") as HTMLInputElement | null;
    const search = searchRegisterInput?.value ?? ""
    return search;
}

const changePlaceHolderByFilter = function () {
    const filter = getFilter();
    // if the input doesnt have a custom place holder is because you should put
    // the name
    const PlaceHolder = PlaholderByFilter[filter!] || "NAME";
    const searchRegisterInput = document.getElementById("SearchRegisterInput") as HTMLInputElement | null;
    searchRegisterInput?.setAttribute("placeholder", PlaceHolder)
}




export { getSearch, getFilter, changePlaceHolderByFilter };







