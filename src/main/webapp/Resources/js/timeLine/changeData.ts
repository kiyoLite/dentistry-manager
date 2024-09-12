import { getShiftInfoForTodayByDentist } from "../CallBackend/getShiftForTodayByDentist.js";
import { consoleErrorStopExecution, consoleErrorWithoutStopExecution } from "../alert/Error/console.js";
import { toastMessage, leftScreenToastError } from "../alert/Error/toast.js";
import { postDataToTimeLine } from "./postData.js";
import { setContainerSize } from "../other/setContinerSize.js";
import { createMilestoneSkeleton } from "./createMilestone.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
import { registerType } from "../CallBackend/getSingleRegister.js";

const getTimeLineData = async function (dentistId: number) {
    let data: { [keys: string]: registerType };
    await getShiftInfoForTodayByDentist(dentistId)
        .then(response => response.json())
        .then((result: { [keys: string]: registerType }) => data = result)
        // if catch is fire this function stop execution else
        // data always be other than NULL
        .catch(e => consoleErrorStopExecution(e));


    return data!
}


const getDentistId = function () {
    const containerAllDentist = document.getElementById("SelectDentist") as HTMLSelectElement | null;
    verifyDOMElementExisteOrError(containerAllDentist);
    const unformattedDentistId = containerAllDentist!.value;
    if (unformattedDentistId === null) {
        const errorMessage: string = "doesn't exist the neccesary attribute on element"
        consoleErrorStopExecution(errorMessage);
    }
    return parseInt(unformattedDentistId!);
}

const changeTimeLineDataByDentist = async function () {
    const dentistId = getDentistId()
    const data = await getTimeLineData(dentistId);
    const TimeLine = document.getElementById("ContainerShiftTimeLine");
    verifyDOMElementExisteOrError(TimeLine);
    setContainerSize(Object.keys(data).length, TimeLine!, createMilestoneSkeleton)
    postDataToTimeLine(data);
}

const changeTimeLine = async function () {
    try {
        await changeTimeLineDataByDentist()
    }
    catch (e) {
        const error = e as TypeError
        consoleErrorWithoutStopExecution(error.message);
        const userErrorDescription = "loading dentist's patient";
        const userErrorTitle = "Error"
        const toast = new toastMessage(userErrorTitle, userErrorDescription);
        leftScreenToastError(toast);
    }
}

export { changeTimeLine }