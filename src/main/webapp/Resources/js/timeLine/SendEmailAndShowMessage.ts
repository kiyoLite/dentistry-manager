import { sendEmailByDentistId } from "../CallBackend/sendEmail.js"
import { isStatusOk } from "../CallBackend/verifyStatus.js"
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js"
const sendEmailOrShowError = async function (dentistId: number) {
    let emailWasSent = false
    await sendEmailByDentistId(dentistId)
        .then((response: Response) => emailWasSent = isStatusOk(response));
    if (!emailWasSent) {
        const errorTitle = "couldn't send the email"
        const errorDescription = "was an error on server"
        const toast = new toastMessage(errorTitle, errorDescription)
        leftScreenToastError(toast);
    }
}

export { sendEmailOrShowError };