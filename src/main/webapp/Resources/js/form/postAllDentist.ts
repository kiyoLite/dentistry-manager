import { getDentists } from "../CallBackend/getDentists.js"


class dentist {
    public readonly name: string;
    public readonly id: number;
    constructor(name: string, id: number) {
        this.name = name;
        this.id = id;
    }


}



const getAllDentistAsArray = async function () {
    let data: any = null;
    await getDentists()
        .then(response => response.json())
        .then(result => data = result)
    if (data === null) {
        return [];
    }
    const datakeys = Object.keys(data);
    const dentists: dentist[] = new Array(datakeys.length);
    for (let i = 0; i < dentists.length; i++) {
        const curData = data[datakeys[i]];
        const curDentist = new dentist(curData.name, curData.id);
        dentists[i] = curDentist;
    }
    return dentists;
}



const FillSelectElementWithDentists = async function (selectElement: HTMLSelectElement) {
    const Collectiondata = await getAllDentistAsArray();
    for (const curData of Collectiondata) {
        const dentistName = curData.name;
        const dentistDBId = curData.id;
        const optionElement = document.createElement("option");
        optionElement.textContent = dentistName;
        optionElement.value = dentistDBId.toString();
        selectElement.add(optionElement);
    }

}

export { FillSelectElementWithDentists }