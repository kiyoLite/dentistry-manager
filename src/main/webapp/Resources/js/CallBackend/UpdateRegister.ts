interface messagingType {
    [k: string]: FormDataEntryValue;
}

const sendDataUpdateRegister = function (registerId: number, data: messagingType) {
    const url = "";
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