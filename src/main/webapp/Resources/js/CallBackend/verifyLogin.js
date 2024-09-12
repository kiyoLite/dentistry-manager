const existLoginInDB = function (user, password) {
    const servletUrl = "http://localhost:8080/DentistryManager/SvVerifyAndRedirectLogin";
    return fetch(servletUrl, {
        method: "POST",
        body: JSON.stringify({
            userName: user,
            password: password
        })
    });
};
export { existLoginInDB };
