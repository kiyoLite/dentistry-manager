/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Persistence.Entities.Patient;
import Persistence.Entities.Shift;
import Persistence.Enums.InsuranceProvider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author soyky
 */
public class JSONBuilder {

    public JSONBuilder() {
    }

    public JSONObject createFromGenericObject(Object[] fields, String[] FieldsNames) {
        JSONObject mainJSON = new JSONObject();
        for (int i = 0; i < FieldsNames.length; i++) {
            Object curField = fields[i];
            String curKey = FieldsNames[i];
            mainJSON.put(curKey, curField);
        }
        return mainJSON;

    }

    public JSONObject createFromListGenericsObjects(List<Object[]> listGenericsObjects, String[] FieldsNames, String JSONGenericKey) {
        int currentGenericObjectCounter = 1;
        JSONObject mainJSON = new JSONObject();
        for (Object[] genericObject : listGenericsObjects) {
            JSONObject subJSON = createFromGenericObject(genericObject, FieldsNames);
            String curKey = JSONGenericKey + currentGenericObjectCounter;
            mainJSON.put(curKey, subJSON);
            currentGenericObjectCounter++;
        }
        return mainJSON;
    }

    public JSONObject createFromShift(Shift shift) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Patient patientData = shift.getPatient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        HashMap<String, Object> shiftData = new HashMap<>();
        shiftData.put("price", shift.getPrice());
        shiftData.put("shiftReason", shift.getReason());
        shiftData.put("scheduling", formatter.format(shift.getScheduling().getTime()));
        shiftData.put("dentistId", shift.getDentist().getId());
        shiftData.put("dentistName", shift.getDentist().getPersonalData().getFirstName());
        shiftData.put("patientEps", patientData.getInsuranceProvider().toString());
        shiftData.put("patientDisability", patientData.getDisability());
        shiftData.put("patientEmail", patientData.getPersonalData().getEmail());
        shiftData.put("patientFirstName", patientData.getPersonalData().getFirstName());
        shiftData.put("patientLastName", patientData.getPersonalData().getLastName());
        shiftData.put("patientBirthDate", formatter.format(patientData.getPersonalData().getBirthDate().getTime()));
        
        return new JSONObject(shiftData);

    }

}
