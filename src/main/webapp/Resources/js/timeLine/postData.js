import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
const orderKeyDataByDateProperty = function (data) {
    const dataKey = Object.keys(data);
    dataKey.sort((a, b) => {
        const dateA = data[a].schedulingTime;
        const dateB = data[b].schedulingTime;
        return dateA > dateB ? 1 : -1;
    });
    return dataKey;
};
const postDataToSingleMilestone = function (milestone, data) {
    //milestone is prefab by a function so if exist all childs shuould exist too
    const containerShiftInfo = milestone.firstElementChild;
    const containerShiftDate = containerShiftInfo?.nextElementSibling;
    const time = containerShiftDate?.lastElementChild;
    verifyDOMElementExisteOrError(time);
    time.textContent = data.schedulingTime;
    const patientName = containerShiftInfo?.firstElementChild;
    verifyDOMElementExisteOrError(patientName);
    patientName.textContent = data.patientFirstName + " " + data.patientLastName;
    const shiftReason = patientName?.nextElementSibling;
    verifyDOMElementExisteOrError(shiftReason);
    shiftReason.textContent = data.shiftReason;
    const patientDisability = shiftReason?.nextElementSibling;
    verifyDOMElementExisteOrError(patientDisability);
    patientDisability.textContent = data.patientDisability === "" ? "no has" : data.patientDisability;
};
const postDataToTimeLine = function (data) {
    const timeLine = document.getElementById("ContainerShiftTimeLine");
    verifyDOMElementExisteOrError(timeLine);
    const dataKeys = orderKeyDataByDateProperty(data);
    const milestones = Array.from(timeLine.children);
    for (let i = 0; i < milestones.length; i++) {
        const curMilestone = milestones[i];
        const curKey = dataKeys[i];
        const curData = data[curKey];
        postDataToSingleMilestone(curMilestone, curData);
    }
};
export { postDataToTimeLine };
