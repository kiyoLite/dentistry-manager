const getRegisterShiftTable = function (config) {
    const SvUrl = "http://localhost:8080/DentistryManager/SvGetShifts";
    const parameters = config.buildUrlParams();
    const SvUrlWithParameters = SvUrl + "?" + parameters.toString();
    return fetch(SvUrlWithParameters, {
        method: 'GET'
    });
};
//create the same enum at backend
var filterType;
(function (filterType) {
    filterType[filterType["price"] = 0] = "price";
    filterType[filterType["scheduling"] = 1] = "scheduling";
    filterType[filterType["dentist"] = 2] = "dentist";
    filterType[filterType["patient"] = 3] = "patient";
    filterType[filterType["default"] = 4] = "default";
})(filterType || (filterType = {}));
class FetchConfig {
    constructor(quantity, startFrom, filter, search, isNextPage) {
        this.quantity = quantity;
        this.startFrom = startFrom;
        this.filter = filter;
        this.search = search;
        this.isNextPage = isNextPage;
    }
    getQuantity() {
        return this.quantity;
    }
    getStartFrom() {
        return this.startFrom;
    }
    getFilter() {
        return this.filter;
    }
    getSearch() {
        return this.search;
    }
    getIsNextPage() {
        return this.isNextPage;
    }
    buildUrlParams() {
        const parameters = new URLSearchParams();
        parameters.append("quantity", this.quantity.toString());
        parameters.append("startFrom", this.startFrom.toString());
        parameters.append("filter", filterType[this.filter].toString());
        parameters.append("search", this.search);
        parameters.append("isNextPage", String(this.isNextPage));
        return parameters;
    }
}
export { getRegisterShiftTable, filterType, FetchConfig };
