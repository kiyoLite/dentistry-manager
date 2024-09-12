import { consoleErrorWithoutStopExecution } from "../alert/Error/console.js";
import { leftScreenToastError, toastMessage } from "../alert/Error/toast.js";
import { getCardData } from "../CallBackend/getCardsData.js";
import { verifyDOMElementExisteOrError } from "../verify/existDomElement.js";

const mokearData = function () {
    return new Promise(

        (resolve, reject) => {

            const data = {
                shift1: {
                    patientInsuranceProvider: 'KAISER_PERMANENTE',
                    dentistId: 1,
                    price: 150.50,
                    patientFirstName: 'John',
                    schedulingTime: '14:30',
                    schedulingDate: '2024-09-10',
                    patientEmail: 'john.doe@example.com',
                    dentistName: 'Dr. Smith',
                    patientBirthDate: '1990-05-15',
                    shiftReason: 'Routine checkup',
                    patientDisability: 'None',
                    patientLastName: 'Doe'
                },
                shift2: {
                    patientInsuranceProvider: 'AETNA',
                    dentistId: 2,
                    price: 200.00,
                    patientFirstName: 'Jane',
                    schedulingTime: '10:00',
                    schedulingDate: '2024-09-11',
                    patientEmail: 'jane.smith@example.com',
                    dentistName: 'Dr. Brown',
                    patientBirthDate: '1985-08-22',
                    shiftReason: 'Cavity filling',
                    patientDisability: 'None',
                    patientLastName: 'Smith'
                }
            };
            setTimeout(() => resolve(data), 3000)
        }
    )
}
const getData = async function () {
    let data;
    await mokearData()
        .then((result) => data = result);
    return data;
};
const sortDataKeys = function (data) {
    let dataKeys = Object.keys(data);
    dataKeys = dataKeys.sort((a, b) => {
        const dataA = data[a];
        const dataB = data[b];
        const scheculingA = new Date(dataA.schedulingDate + "T" + dataA.schedulingTime);
        const schedulingB = new Date(dataB.schedulingDate + "T" + dataB.schedulingTime);
        return scheculingA > schedulingB ? 1 : -1;
    });
    console.log(dataKeys)
    return dataKeys;
};
const postDataToSingleCard = function (data, card) {
    const cardId = card.id;
    const price = document.querySelector(`#${cardId} .price `);
    verifyDOMElementExisteOrError(price);
    price.textContent = data.price.toString();
    const patientName = document.querySelector(`#${cardId} .patientName`);
    verifyDOMElementExisteOrError(patientName);
    patientName.textContent = data.patientFirstName;
    const insuranceProvider = document.querySelector(`#${cardId} .insuranceProvier`);
    verifyDOMElementExisteOrError(insuranceProvider);
    insuranceProvider.textContent = data.patientInsuranceProvider;
    const scheduling = document.querySelector(`#${cardId} .scheduling`);
    verifyDOMElementExisteOrError(scheduling);
    scheduling.textContent = data.schedulingDate + " " + data.schedulingTime;
    const dentist = document.querySelector(`#${cardId} .dentistName`);
    verifyDOMElementExisteOrError(dentist);
    dentist.textContent = data.dentistName;
};
const postDataToCards = async function () {
    const data = await getData();
    const previousShiftCard = document.getElementById("previousShift");
    const nextShiftCard = document.getElementById("nextShift");
    verifyDOMElementExisteOrError(previousShiftCard);
    verifyDOMElementExisteOrError(nextShiftCard);
    const dataKeys = sortDataKeys(data);
    // cards must be in that order beacuase , datakeys is sorted in ascending order so  last/previous shift is filling first.js; 
    const cards = [previousShiftCard, nextShiftCard];
    for (let i = 0; i < cards.length; i++) {
        const card = cards[i];
        const curKey = dataKeys[i];
        const curData = data[curKey];
        postDataToSingleCard(curData, card);
    }
};
const tryPostCardsData = async function () {
    try {
        await postDataToCards();
    }
    catch (e) {
        const error = e;
        consoleErrorWithoutStopExecution(error.message);
        const userErrorDescription = "couldn't update cards";
        const userErrorTitle = "Error";
        const toast = new toastMessage(userErrorTitle, userErrorDescription);
        leftScreenToastError(toast);
    }
};
export { tryPostCardsData };
