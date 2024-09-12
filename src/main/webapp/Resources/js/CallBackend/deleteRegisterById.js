const deleteRegister = function (Id) {
    const url = "";
    return fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'text/plain'
        },
        body: Id.toString()
    });
};
export { deleteRegister };
