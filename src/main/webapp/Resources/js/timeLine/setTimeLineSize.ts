import { createMilestoneSkeleton } from "./createMilestone.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";


const createNeccesaryMilestones = function (missingMilestone: number, milestonesContiner: HTMLDivElement) {
    const MilestonesHtml = createMilestoneSkeleton(missingMilestone);
    milestonesContiner.insertAdjacentHTML("beforeend", MilestonesHtml);
}


const removeExcessMilestones = function (Milestones: Element[], quantityExcessMilestones: number) {
    let indexCurMilestone = Milestones.length - 1;
    while (indexCurMilestone >= Milestones.length - quantityExcessMilestones) {
        const curMilestone = Milestones[indexCurMilestone];
        curMilestone.remove();
        indexCurMilestone--;
    }
}
const setTimeLineSize = function (neccesaryMilestones: number) {
    const TimeLine = document.getElementById("ContainerShiftTimeLine") as HTMLDivElement;
    verifyDOMElementExisteOrError(TimeLine);
    const Milestones = Array.from(TimeLine!.children);
    if (Milestones.length > neccesaryMilestones) {
        const quantityExcessMilestones = Milestones.length - neccesaryMilestones;
        removeExcessMilestones(Milestones, quantityExcessMilestones);
    }
    else {
        const missingMilestones = neccesaryMilestones - Milestones.length;
        createNeccesaryMilestones(missingMilestones, TimeLine);
    }
}


export { setTimeLineSize }