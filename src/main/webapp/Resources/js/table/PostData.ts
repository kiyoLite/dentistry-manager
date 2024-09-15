import { CollectionRegisterShiftTable, registerShiftTable } from "../CallBackend/getRegisters.js";


const keysFromRegisterShiftTable: Record<number, string> = {
    0: "patientName",
    1: "dentistName",
    2: "scheduling",
    3: "email",
    4: "price",
}



const postDataSingleRow = function (rowData: registerShiftTable, row: HTMLTableRowElement) {
    const rowFields: HTMLTableCellElement[] = Array.from(row.cells);
    const NameAttributeDBId = "data-Id";
    row.setAttribute(NameAttributeDBId, rowData.id);
    for (let i = 0; i < rowFields.length - 1; i++) {
        const curField: HTMLTableCellElement = rowFields[i];
        const curKey = keysFromRegisterShiftTable[i] as keyof typeof rowData;
        const fieldData = rowData[curKey].toString();
        curField.textContent = fieldData;
    }

}


const postDataToRows = function (rowsData: CollectionRegisterShiftTable, containerRows: HTMLTableElement) {
    const rows = Array.from(containerRows.rows);
    const rowsDataKeys = Array.from(Object.keys(rowsData));
    for (let i = 0; i < rows.length; i++) {
        const curRow = rows[i];
        const curKey = rowsDataKeys[i];
        const curData: registerShiftTable = rowsData[curKey];
        postDataSingleRow(curData, curRow);
    }
}




export { postDataSingleRow, postDataToRows }
