const sendDataCreateRegister = function (data) {
    const url = "http://localhost:8080/DentistryManager/SvCreateShift";
    return fetch(url, {
        method: "POST",
        body: JSON.stringify(data)
    });
};
export { sendDataCreateRegister };
