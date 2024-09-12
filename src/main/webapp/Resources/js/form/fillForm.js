import { consoleErrorStopExecution } from "../alert/Error/console.js";
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js";
import { getSingleRegister } from "../CallBackend/getSingleRegister.js";
import { isStatusOk } from "../CallBackend/verifyStatus.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";
const fillForm = function (form, data) {
    const formId = form.id;
    const dataKeys = Object.keys(data);
    for (let i = 0; i < dataKeys.length; i++) {
        const curKey = dataKeys[i];
        const curElement = document.querySelector(`#${formId} *[name="${curKey}"]`);
        if (curElement && curElement.tagName !== "SELECT") {
            const curData = data[curKey];
            curElement.value = curData;
        }
    }
};
const selectDefaultOption = function (select, expectDefaultValue) {
    const oldValue = select.value;
    select.value = expectDefaultValue;
    if (select.value === "")
        select.value = oldValue;
};
const errorGetFormData = function (httpStatus) {
    const errorTitle = "error filling form";
    const description = "couldn't get data";
    const toast = new toastMessage(errorTitle, description);
    leftScreenToastError(toast);
    const specifErrorMessage = "Sv response with" + httpStatus + " status , while recovering data";
    consoleErrorStopExecution(specifErrorMessage);
};
const getData = async function (registerId) {
    let data = null;
    await getSingleRegister(registerId)
        .then(response => {
        if (isStatusOk(response)) {
            const httpStatus = response.status;
            errorGetFormData(httpStatus);
        }
        return response.json();
    })
        .then((result) => data = result);
    return data;
};
const fillUpdateRegisterForm = async function (registerId) {
    let data = await getData(registerId);
    const uploadForm = document.getElementById("FormCreateShift");
    const insuranceProviderField = document.querySelector(`select[name="patientInsuranceProvider"]`);
    const dentistField = document.querySelector(`select[name="dentist"]`);
    verifyDOMElementExisteOrError(uploadForm);
    verifyDOMElementExisteOrError(insuranceProviderField);
    verifyDOMElementExisteOrError(dentistField);
    fillForm(uploadForm, data);
    selectDefaultOption(insuranceProviderField, data.patientInsuranceProvider);
    selectDefaultOption(dentistField, data.dentistName);
};
export { fillForm, selectDefaultOption, fillUpdateRegisterForm };
