const getDentists = function () {
    const url = "http://localhost:8080/DentistryManager/SvIdAndDentistName";
    return fetch(url, { method: "GET" });
};
export { getDentists };
