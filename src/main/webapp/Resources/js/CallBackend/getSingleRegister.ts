const getSingleRegister = function (registerId: number) {
    const url = "http://localhost:8080/DentistryManager/SvGetSingleShift"
    return fetch(url,
        {
            method: "GET",
            headers: { 'Content-Type': 'text/plain' },
            body: registerId.toString()
        });
}
interface registerType {
    patientInsuranceProvider: string;
    dentistId: number;
    price: number;
    patientFirstName: string;
    schedulingTime: string;
    schedulingDate: string;
    patientEmail: string;
    dentistName: string;
    patientBirthDate: string;
    shiftReason: string;
    patientDisability: string;
    patientLastName: string;

}
export { getSingleRegister, registerType }