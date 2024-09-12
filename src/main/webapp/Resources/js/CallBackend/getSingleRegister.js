const getSingleRegister = function (registerId) {
    const url = "";
    return fetch(url, {
        method: "GET",
        headers: { 'Content-Type': 'text/plain' },
        body: registerId.toString()
    });
};
export { getSingleRegister };
