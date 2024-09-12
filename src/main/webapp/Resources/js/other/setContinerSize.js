const addMissingElements = function (qunatityMissingElements, container, createSkeletonElements) {
    //the function must only create the skeleton 
    const ElementsHtml = createSkeletonElements(qunatityMissingElements);
    container.insertAdjacentHTML("beforeend", ElementsHtml);
};
const removerExcessElements = function (AllElements, quantityExcessMilestones) {
    let indexCurElement = AllElements.length - 1;
    while (indexCurElement >= AllElements.length - quantityExcessMilestones) {
        const curElement = AllElements[indexCurElement];
        curElement.remove();
        indexCurElement--;
    }
};
const setContainerSize = function (neccesaryElements, container, funcCreateElement) {
    const curChildren = Array.from(container.children);
    if (curChildren.length > neccesaryElements) {
        const quantityExcessElements = curChildren.length - neccesaryElements;
        removerExcessElements(curChildren, quantityExcessElements);
    }
    else {
        const missingElements = neccesaryElements - curChildren.length;
        addMissingElements(missingElements, container, funcCreateElement);
    }
};
export { setContainerSize };
