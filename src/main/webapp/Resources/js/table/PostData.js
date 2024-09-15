const keysFromRegisterShiftTable = {
    0: "patientName",
    1: "dentistName",
    2: "scheduling",
    3: "email",
    4: "price"
};
const postDataSingleRow = function (rowData, row) {
    const rowFields = Array.from(row.cells);
    const NameAttributeDBId = "data-Id";
    row.setAttribute(NameAttributeDBId, rowData.id);
    for (let i = 0; i < rowFields.length - 1; i++) {
        const curField = rowFields[i];
        const curKey = keysFromRegisterShiftTable[i];
        const fieldData = rowData[curKey].toString();
        curField.textContent = fieldData;
    }
};
const postDataToRows = function (rowsData, containerRows) {
    const rows = Array.from(containerRows.rows);
    const rowsDataKeys = Array.from(Object.keys(rowsData));
    for (let i = 0; i < rows.length; i++) {
        const curRow = rows[i];
        const curKey = rowsDataKeys[i];
        const curData = rowsData[curKey];
        postDataSingleRow(curData, curRow);
    }
};
export { postDataSingleRow, postDataToRows };
