import { consoleErrorStopExecution } from "../alert/Error/console.js"

const verifyDOMElementExisteOrError = function (element: HTMLElement | null | undefined) {
    if (element === null || element === undefined) {
        const errorMessage = "couldn't get neccesary dom element"
        consoleErrorStopExecution(errorMessage);
    }
}

export { verifyDOMElementExisteOrError }