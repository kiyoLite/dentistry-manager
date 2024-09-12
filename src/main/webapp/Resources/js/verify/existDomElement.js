import { consoleErrorStopExecution } from "../alert/Error/console.js";
const verifyDOMElementExisteOrError = function (element) {
    if (element === null || element === undefined) {
        const errorMessage = "couldn't get neccesary dom element";
        consoleErrorStopExecution(errorMessage);
    }
};
export { verifyDOMElementExisteOrError };
