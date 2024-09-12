const addHighlightRow = function (event: Event) {
    const specificField = event.target as HTMLTableCellElement | null;
    const closestRow = specificField?.closest("tr");
    closestRow?.classList.add("HighlightRow");
}

const removeHighlightrow = function () {
    const highlightRow = document.querySelector(".HighlightRow");
    highlightRow?.classList.remove("HighlightRow");
}

const highlightSingleRow = function (event: Event) {
    removeHighlightrow();
    addHighlightRow(event);
}

const getHightlightRow = function () {
    const highlightRow = document.querySelector(".HighlightRow");
    return highlightRow;

}

const getDBIdFromHighLightRow = function () {
    const highlightRow = document.querySelector(".HighlightRow") as HTMLTableRowElement | null;
    const attributeDBIdName = "data-Id";
    const unformattedId = highlightRow?.getAttribute(attributeDBIdName)
    if (unformattedId === null || unformattedId === undefined) return null;
    const formattedId = parseInt(unformattedId!);
    return formattedId;

}


export { getDBIdFromHighLightRow, highlightSingleRow, getHightlightRow }

