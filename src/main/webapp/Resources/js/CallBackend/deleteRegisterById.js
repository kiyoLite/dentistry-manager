const deleteRegister = function (Id) {
    const url = "http://localhost:8080/DentistryManager/SvDeleteShift";
    return fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'text/plain'
        },
        body: Id.toString()
    });
};
export { deleteRegister };
