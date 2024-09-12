import { existLoginInDB } from "../CallBackend/verifyLogin.js";
import { isStatusOk } from "../CallBackend/verifyStatus.js";
import { isFormContentCorrect } from "../verify/form.js";
import { errorElementsInForm } from "../alert/Error/form.js";
import { errorButton, ButtonErrorType } from "../alert/Error/button.js";
let userName = "User"

const inputUserName = document.getElementById("UserName") as HTMLInputElement | null;
const inputPassword = document.getElementById("password") as HTMLInputElement | null;
const buttonSubmitForm = document.getElementById("submitFormButton") as HTMLButtonElement | null;
const loginForm = document.getElementById("formLogin") as HTMLFormElement | null;
const showPasswordCheckBox = document.getElementById("ShowPasswordCheckbox") as HTMLInputElement | null;


const togglePasswordVisibility = function () {
    if (showPasswordCheckBox?.checked) {
        inputPassword!.type = "text"
    }
    else {
        inputPassword!.type = "password"
    }
}

loginForm?.addEventListener("submit", (e) => e.preventDefault());

buttonSubmitForm?.addEventListener("click", () => {
    const form = loginForm!;
    const isFormCorrect = isFormContentCorrect(form);
    if (!isFormCorrect) {
        errorElementsInForm(form);
        return;
    }
    const user = inputUserName?.value ?? ""
    const password = inputPassword?.value ?? ""
    userName = user;
    existLoginInDB(user, password)
        .then(response => {
            if (!isStatusOk(response)) {
                errorButton(buttonSubmitForm, ButtonErrorType.noFind);
                userName = "User"
            }

        })


})

showPasswordCheckBox?.addEventListener("change", togglePasswordVisibility)

export { userName }





