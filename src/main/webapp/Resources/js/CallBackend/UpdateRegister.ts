interface messagingType {
    [k: string]: FormDataEntryValue;
}

const sendDataUpdateRegister = function (registerId: number, data: messagingType) {
    const url = "http://localhost:8080/DentistryManager/SvUpdateShift";
    return fetch(
        url,
        {
            method: "POST",
            body: JSON.stringify({
                "registerData": data,
                "registerId": registerId

            })
        })
}

export { sendDataUpdateRegister }