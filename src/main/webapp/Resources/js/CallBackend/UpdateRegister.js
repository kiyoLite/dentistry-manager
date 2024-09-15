const sendDataUpdateRegister = function (registerId, data) {
    const url = "http://localhost:8080/DentistryManager/SvUpdateShift";
    return fetch(url, {
        method: "POST",
        body: JSON.stringify({
            "registerData": data,
            "registerId": registerId
        })
    });
};
export { sendDataUpdateRegister };
