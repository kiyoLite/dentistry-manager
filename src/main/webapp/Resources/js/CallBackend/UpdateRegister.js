const sendDataUpdateRegister = function (registerId, data) {
    const url = "";
    return fetch(url, {
        method: "POST",
        body: JSON.stringify({
            "registerData": data,
            "registerId": registerId
        })
    });
};
export { sendDataUpdateRegister };
