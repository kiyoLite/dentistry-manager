interface messagingType {
    [k: string]: FormDataEntryValue;
}

const sendDataCreateRegister = function (data: messagingType) {
    const url = "http://localhost:8080/DentistryManager/SvCreateShift";
    return fetch(
        url,
        {
            method: "POST",
            body: JSON.stringify(data)
        })
}

export { sendDataCreateRegister }