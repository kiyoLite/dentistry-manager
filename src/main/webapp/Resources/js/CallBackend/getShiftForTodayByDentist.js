const getShiftInfoForTodayByDentist = function (dentistId) {
    const url = "";
    return fetch(url, {
        method: "GET",
        headers: {
            'Content-Type': 'text/plain'
        },
        body: dentistId.toString()
    });
};
export { getShiftInfoForTodayByDentist };
