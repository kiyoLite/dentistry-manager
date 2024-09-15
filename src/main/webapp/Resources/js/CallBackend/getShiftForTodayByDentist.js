const getShiftInfoForTodayByDentist = function (dentistId) {
    const url = "http://localhost:8080/DentistryManager/SvgetShiftForTodayByDentist";
    return fetch(url, {
        method: "GET",
        headers: {
            'Content-Type': 'text/plain'
        },
        body: dentistId.toString()
    });
};
export { getShiftInfoForTodayByDentist };
