const consoleErrorWithoutStopExecution = function (message: string) {
    console.error(message);
}

const consoleErrorStopExecution = function (message: string) {
    throw new TypeError(message);
}

export { consoleErrorStopExecution, consoleErrorWithoutStopExecution }