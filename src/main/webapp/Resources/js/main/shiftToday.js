import { consoleErrorStopExecution } from "../alert/Error/console.js";
import { FillSelectElementWithDentists } from "../form/postAllDentist.js";
import { changeTimeLine } from "../timeLine/changeData.js";
import { sendEmailOrShowError } from "../timeLine/SendEmailAndShowMessage.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
const listDentistContainer = document.getElementById("SelectDentist");
const SendEmailButton = document.getElementById("SendEmailButton");
const initialize = async function (){
    await FillSelectElementWithDentists(listDentistContainer);
    await changeTimeLine();
}
initialize()
listDentistContainer?.addEventListener("change", changeTimeLine);
SendEmailButton?.addEventListener("click", () => {
    const containerAllDentist = document.getElementById("SelectDentist");
    verifyDOMElementExisteOrError(containerAllDentist);
    const SelectedDentist = listDentistContainer.selectedOptions[0];
    const unformattedDentistId = SelectedDentist.getAttribute("data-milestonId");
    if (unformattedDentistId === null) {
        const errorMessage = "doesn't exist the neccesary attribute on element";
        consoleErrorStopExecution(errorMessage);
    }
    const formattedDentistId = parseInt(unformattedDentistId);
    sendEmailOrShowError(formattedDentistId);
});
