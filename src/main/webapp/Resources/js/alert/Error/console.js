const consoleErrorWithoutStopExecution = function (message) {
    console.error(message);
};
const consoleErrorStopExecution = function (message) {
    throw new TypeError(message);
};
export { consoleErrorStopExecution, consoleErrorWithoutStopExecution };
