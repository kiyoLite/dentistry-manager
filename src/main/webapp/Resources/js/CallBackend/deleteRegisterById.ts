const deleteRegister = function (Id: number) {
    const url = "";
    return fetch(
        url,
        {
            method: "POST",
            headers: {
                'Content-Type': 'text/plain'
            },
            body: Id.toString()
        }
    )
}

export { deleteRegister }