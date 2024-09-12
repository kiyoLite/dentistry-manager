import { registerType } from "../CallBackend/getSingleRegister.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
const orderKeyDataByDateProperty = function (data: { [key: string]: registerType }): string[] {
    const dataKey = Object.keys(data);
    dataKey.sort((a, b) => {
        const dateA = data[a].schedulingTime;
        const dateB = data[b].schedulingTime;
        return dateA > dateB ? 1 : -1;
    })

    return dataKey;
}


const postDataToSingleMilestone = function (milestone: HTMLDivElement, data: registerType) {
    //milestone is prefab by a function so if exist all childs shuould exist too
    const containerShiftInfo = milestone.firstElementChild;
    const containerShiftDate = containerShiftInfo?.nextElementSibling;

    const time = containerShiftDate?.lastElementChild;
    verifyDOMElementExisteOrError(time as HTMLElement);
    time!.textContent = data.schedulingTime;
    const patientName = containerShiftInfo?.firstElementChild;
    verifyDOMElementExisteOrError(patientName as HTMLElement)
    patientName!.textContent = data.patientFirstName + " " + data.patientLastName;
    const shiftReason = patientName?.nextElementSibling;
    verifyDOMElementExisteOrError(shiftReason as HTMLElement);
    shiftReason!.textContent = data.shiftReason;
    const patientDisability = shiftReason?.nextElementSibling;
    verifyDOMElementExisteOrError(patientDisability as HTMLElement);
    patientDisability!.textContent = data.patientDisability === "" ? "no has" : data.patientDisability;
}


const postDataToTimeLine = function (data: { [key: string]: registerType }) {
    const timeLine = document.getElementById("ContainerShiftTimeLine");
    verifyDOMElementExisteOrError(timeLine);
    const dataKeys = orderKeyDataByDateProperty(data);
    const milestones = Array.from(timeLine!.children);
    for (let i = 0; i < milestones.length; i++) {
        const curMilestone = milestones[i] as HTMLDivElement;
        const curKey = dataKeys[i];
        const curData = data[curKey];
        postDataToSingleMilestone(curMilestone, curData);
    }

}

export { postDataToTimeLine }
