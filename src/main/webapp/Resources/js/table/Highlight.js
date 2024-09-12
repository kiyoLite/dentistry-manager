const addHighlightRow = function (event) {
    const specificField = event.target;
    const closestRow = specificField?.closest("tr");
    closestRow?.classList.add("HighlightRow");
};
const removeHighlightrow = function () {
    const highlightRow = document.querySelector(".HighlightRow");
    highlightRow?.classList.remove("HighlightRow");
};
const highlightSingleRow = function (event) {
    removeHighlightrow();
    addHighlightRow(event);
};
const getHightlightRow = function () {
    const highlightRow = document.querySelector(".HighlightRow");
    return highlightRow;
};
const getDBIdFromHighLightRow = function () {
    const highlightRow = document.querySelector(".HighlightRow");
    const attributeDBIdName = "data-Id";
    const unformattedId = highlightRow?.getAttribute(attributeDBIdName);
    if (unformattedId === null || unformattedId === undefined)
        return null;
    const formattedId = parseInt(unformattedId);
    return formattedId;
};
export { getDBIdFromHighLightRow, highlightSingleRow, getHightlightRow };
