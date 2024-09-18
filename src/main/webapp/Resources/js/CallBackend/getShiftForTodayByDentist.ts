const getShiftInfoForTodayByDentist = function (dentistId: number) {
    const url = "http://localhost:8080/DentistryManager/SvgetShiftForTodayByDentist"
    return fetch(
        url,
        {
            method: "POST",
            headers: {
                'Content-Type': 'text/plain'
            },
            body: dentistId.toString()

        }

    )
}



export { getShiftInfoForTodayByDentist }