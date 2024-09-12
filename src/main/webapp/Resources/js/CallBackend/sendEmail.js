const sendEmailByDentistId = function (dentistId) {
    const url = "";
    return fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'text/plain'
        },
        body: dentistId.toString()
    });
};
export { sendEmailByDentistId };
