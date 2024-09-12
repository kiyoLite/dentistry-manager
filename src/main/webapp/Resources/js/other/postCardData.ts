import { consoleErrorWithoutStopExecution } from "../alert/Error/console.js";
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js";
import { getCardData } from "../CallBackend/getCardsData.js"
import { registerType } from "../CallBackend/getSingleRegister.js"
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";

const getData = async function () {
    let data: { [key: string]: registerType };
    await getCardData()
        .then(response => response.json())
        .then((result: { [key: string]: registerType }) => data = result)
    return data!
}

const sortDataKeys = function (data: { [key: string]: registerType }) {
    const dataKeys = Object.keys(data);
    dataKeys.sort((a, b) => {
        const dataA = data[a];
        const dataB = data[b];
        const scheculingA = new Date(dataA.schedulingDate + "T" + dataA.schedulingTime);
        const schedulingB = new Date(dataB.schedulingDate + "T" + dataB.schedulingTime);

        return scheculingA > schedulingB ? 1 : -1;
    })
    return dataKeys
}

const postDataToSingleCard = function (data: registerType, card: HTMLDivElement) {
    const cardId = card.id;
    const price = document.querySelector(`#${cardId} .price `)
    verifyDOMElementExisteOrError(price as HTMLElement)
    price!.textContent = data.price.toString();
    const patientName = document.querySelector(`#${cardId} .patientName`)
    verifyDOMElementExisteOrError(patientName as HTMLElement)
    patientName!.textContent = data.patientFirstName;
    const insuranceProvider = document.querySelector(`#${cardId} .insuranceProvier`)
    verifyDOMElementExisteOrError(insuranceProvider as HTMLElement)
    insuranceProvider!.textContent = data.patientInsuranceProvider;
    const scheduling = document.querySelector(`#${cardId} .scheduling`)
    verifyDOMElementExisteOrError(scheduling as HTMLElement)
    scheduling!.textContent = data.schedulingDate + " " + data.schedulingTime;
    const dentist = document.querySelector(`#${cardId} .dentistName`)
    verifyDOMElementExisteOrError(dentist as HTMLElement)
    dentist!.textContent = data.dentistName;


}

const postDataToCards = async function () {
    const data: { [key: string]: registerType } = await getData();
    const previousShiftCard = document.getElementById("previousShift") as HTMLDivElement | null;
    const nextShiftCard = document.getElementById("nextShift") as HTMLDivElement | null;
    verifyDOMElementExisteOrError(previousShiftCard);
    verifyDOMElementExisteOrError(nextShiftCard);
    const dataKeys = sortDataKeys(data);
    // cards must be in that order beacuase , datakeys is sorted in ascending order so  last/previous shift is filling first; 
    const cards = [previousShiftCard!, nextShiftCard!]
    for (let i = 0; i < cards.length; i++) {
        const card = cards[i];
        const curKey = dataKeys[i];
        const curData = data[curKey];
        postDataToSingleCard(curData, card);
    }


}

const tryPostCardsData = async function () {
    try {
        await postDataToCards()
    }
    catch (e) {
        const error = e as TypeError
        consoleErrorWithoutStopExecution(error.message);
        const userErrorDescription = "couldn't update cards";
        const userErrorTitle = "Error"
        const toast = new toastMessage(userErrorTitle, userErrorDescription);
        leftScreenToastError(toast);
    }
}

export { tryPostCardsData }