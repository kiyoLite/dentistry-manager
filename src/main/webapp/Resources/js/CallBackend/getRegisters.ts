const getRegisterShiftTable = function (config: FetchConfig) {
    const SvUrl: string = "http://localhost:8080/DentistryManager/SvGetShifts";
    const parameters = config.buildUrlParams();
    const SvUrlWithParameters = SvUrl + "?" + parameters.toString();
    return fetch(SvUrlWithParameters, {
        method: 'GET'
    })
}

//create the same enum at backend
enum filterType {
    PRICE, SCHEDULING, DENTIST, PATIENTS, PREDETERMINED
}
interface registerShiftTable {
    id: string;
    price: number;
    contact: string;
    scheduling: string;
    dentistName: string;
    patientName: string;
}

interface CollectionRegisterShiftTable {
    [key: string]: registerShiftTable
}

class FetchConfig {
    private quantity: number;
    private startFrom: number;
    private filter: filterType;
    private search: string;
    private isNextPage: boolean;

    constructor(
        quantity: number,
        startFrom: number,
        filter: filterType,
        search: string,
        isNextPage: boolean,
    ) {
        this.quantity = quantity;
        this.startFrom = startFrom;
        this.filter = filter;
        this.search = search;
        this.isNextPage = isNextPage;
    }

    getQuantity(): number {
        return this.quantity;
    }

    getStartFrom(): number {
        return this.startFrom;
    }

    getFilter(): filterType {
        return this.filter;
    }

    getSearch(): string {
        return this.search;
    }

    getIsNextPage(): boolean {
        return this.isNextPage;
    }

    buildUrlParams() {
        const parameters = new URLSearchParams();
        parameters.append("quantity", this.quantity.toString());
        parameters.append("startFrom", this.startFrom.toString());
        parameters.append("filter", filterType[this.filter].toString());
        parameters.append("search", this.search);
        parameters.append("isNextPage", String(this.isNextPage));
        return parameters
    }


}




export { getRegisterShiftTable, registerShiftTable, CollectionRegisterShiftTable, filterType, FetchConfig } 