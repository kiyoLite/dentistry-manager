/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Persistence.DAO.Implementation.DentistDAOImp;
import Persistence.Entities.Dentist;
import Persistence.Entities.Patient;
import Persistence.Entities.Person;
import Persistence.Entities.Shift;
import Persistence.Enums.InsuranceProvider;
import java.util.Calendar;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author soyky
 */
public class BuildEntityDBFromJson {
//json format :
//    {
//  "dentist": "1",
//  "patientBirthDate": "2007-04-19",
//  "patientDisability": "",
//  "patientEmail": "kiyodeveloper@gmail.com",
//  "PatientInsuranceProvider": "Sanitas",
//  "patientFirstName": "kiyo",
//  "patientLastName": "dev",
//  "shiftDate": "2024-08-25",
//  "shiftPrice": "70",
//  "shiftReason": "fill bad",
//  "shiftTime": "14:30"
//}

    public Shift createShift(JSONObject json) {
        int price = Integer.parseInt(json.getString("shiftPrice"));

        String ShiftDate = json.getString("shiftDate");
        String ShiftTime = json.getString("shiftTime");
        String unformattedShiftDateTime = ShiftDate + "-" + ShiftTime;
        String ShiftDateTime = unformattedShiftDateTime.replace(':', '-');
        List<Integer> schedulingPart = ExctractAndConvertDate.extractDateTime(ShiftDateTime, "-");
        Calendar scheduling = ExctractAndConvertDate.convertToDateTime(schedulingPart);
        String reason = json.getString("shiftReason");

        return new Shift(
                createPatient(json),
                createDentist(json),
                scheduling,
                reason,
                price
        );

    }

    public Dentist createDentist(JSONObject json) {
        long dentistId = Long.parseLong(json.getString("dentist"));
        DentistDAOImp dentistDAO = new DentistDAOImp();
        return dentistDAO.getById(dentistId);
    }

    public Patient createPatient(JSONObject json) {
        String disability = json.getString("patientDisability");
        String unformattedInsuranceProvider = json.getString("PatientInsuranceProvider");
        return new Patient(
                disability.equals("") ? null : disability,
                InsuranceProvider.valueOf(unformattedInsuranceProvider),
                createPersonPatient(json)
        );
    }

    public Person createPersonPatient(JSONObject json) {
        String unfformatedBirthday = json.getString("patientBirthDate");
        List<Integer> birthDateParts = ExctractAndConvertDate.extractDate(unfformatedBirthday, "-");
        return new Person(
                json.getString("patientFirstName"),
                json.getString("patientLastName"),
                ExctractAndConvertDate.convertToDate(birthDateParts),
                json.getString("patientBirthDate")
        );
    }

}
