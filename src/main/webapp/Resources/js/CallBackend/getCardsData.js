const getCardData = function () {
    const url = "http://localhost:8080/DentistryManager/SvgetCardsData";
    return fetch(url, { method: "GET" });
};
export { getCardData };
