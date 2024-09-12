const sendDataCreateRegister = function (data) {
    const url = "";
    return fetch(url, {
        method: "POST",
        body: JSON.stringify(data)
    });
};
export { sendDataCreateRegister };
