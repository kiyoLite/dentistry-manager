import { getSingleRegister, registerType } from "../CallBackend/getSingleRegister.js"
import { getDBIdFromHighLightRow } from "./Highlight.js";
const getData = async function (registerId: number) {
    let data = null;
    await getSingleRegister(registerId)
        .then(response => response.json())
        .then((result: registerType) => data = result)
    return data!;
}
const createWindow = function () {
    const windowHtml = ` <div id="background">
        <div id="title">
            SHIFT INFORMATION
            <img src="../images/close(x).svg" alt="" id="buttonCloseWindow">
        </div>
        <div id="moreData">
            <ul>
                <li>
                    <strong>patient name</strong>
                    <p id="patientFirstName"></p>
                </li>
                <li>
                    <strong> dentist name</strong>
                    <p id="dentistName"></p>
                </li>
                <li>
                    <strong>scheduling</strong>
                    <p id="scheduling"></p>
                </li>
                <li>
                    <strong>patient email</strong>
                    <p id="patientEmail"></p>
                </li>
                <li>
                    <strong>price</strong>
                    <p id="price"></p>
                </li>
                <li>
                    <strong>reason</strong>
                    <p id="shiftReason"></p>
                </li>
                <li>
                    <strong>age</strong>
                    <p id="age"></p>
                </li>
                <li>
                    <strong>insarance provider</strong>
                    <p id="patientInsuranceProvider"</p>
                </li>
                <li>
                    <strong>disability</strong>
                    <p id="patientDisability"></p>
                </li>


            </ul>
        </div>

    </div>`

    document.body.insertAdjacentHTML("beforeend", windowHtml);
}

const postData = function (data: registerType) {
    const dataKeys = Object.keys(data);
    for (let i of dataKeys) {
        const curField = document.getElementById(i);
        if (curField !== null) {
            const curData = data[i as keyof typeof data];
            curField.textContent = curData.toString();
        }
    }

    const birthDate = new Date(data.patientBirthDate);
    const today = new Date();
    const birthDateYear = birthDate.getFullYear();
    const todayYear = today.getFullYear();
    birthDate.setFullYear(todayYear);
    const age = birthDate < today ? todayYear - birthDateYear : todayYear - birthDateYear - 1;
    document.getElementById("age")!.textContent = age.toString();

    const disability = data.patientDisability === "" ? "has no" : data.patientDisability;
    document.getElementById("patientDisability")!.textContent = disability;

    document.getElementById("buttonCloseWindow")?.addEventListener("click", () => {
        document.getElementById("background")?.remove();
    });


}

const showMoreInfo = async function (event: Event) {
    const rowField = event.target as HTMLTableCellElement;
    //read more button/cell ( ... ) always  be the last element , so if cell doesn't have nextSibling the pressed cell is "read more"
    if (rowField.nextElementSibling !== null) return
    // when you click a row this is highlight , so you can get easier the row id , using this method

    const rowId = getDBIdFromHighLightRow();
    if (rowId !== null) {
        const data = await getData(rowId);
        createWindow()
        postData(data);
    }
}

export { showMoreInfo }