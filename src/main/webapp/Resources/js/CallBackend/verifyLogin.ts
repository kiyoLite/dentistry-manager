const existLoginInDB = function (user: string, password: string) {
    const servletUrl: string = "";
    return fetch(servletUrl, {
        method: "GET",
        body: JSON.stringify({
            userName: user,
            password: password
        })
    })
}

export { existLoginInDB }