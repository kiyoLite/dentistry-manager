const getSingleRegister = function (registerId) {
    const url = "http://localhost:8080/DentistryManager/SvGetSingleShift";
    return fetch(url, {
        method: "POST",
        headers: { 'Content-Type': 'text/plain' },
        body: registerId.toString()
    });
};
export { getSingleRegister };
