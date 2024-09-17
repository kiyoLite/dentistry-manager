/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Persistence.Entities.Patient;
import Persistence.Entities.Person;
import Persistence.Entities.Shift;
import Persistence.Enums.InsuranceProvider;
import java.util.Calendar;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class BuildEntityDBFromJsonTest {

    JSONObject json;

    @BeforeEach
    public void setUp() {
        String jsonString = "{"
                + "\"dentist\": \"1\","
                + "\"patientBirthDate\": \"2007-04-19\","
                + "\"patientDisability\": \"\","
                + "\"patientEmail\": \"kiyodeveloper@gmail.com\","
                + "\"patientInsuranceProvider\": \"AETNA\","
                + "\"patientFirstName\": \"kiyo\","
                + "\"patientLastName\": \"dev\","
                + "\"schedulingDate\": \"2024-08-25\","
                + "\"price\": \"70\","
                + "\"shiftReason\": \"feel bad\","
                + "\"schedulingTime\": \"14:30\""
                + "}";
        json = new JSONObject(jsonString);
    }

    @AfterEach
    public void tearDown() {
        json = null;
    }

    @Test
    public void createPersonPatient() {
        BuildEntityDBFromJson build = new BuildEntityDBFromJson();
        Person person = build.createPersonPatient(json);
        Assertions.assertNotNull(person);
        Assertions.assertEquals("kiyo", person.getFirstName());
        Assertions.assertEquals("dev", person.getLastName());

        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2007, Calendar.APRIL, 19);
        Assertions.assertEquals(expectedDate.get(Calendar.YEAR), person.getBirthDate().get(Calendar.YEAR));
        Assertions.assertEquals(expectedDate.get(Calendar.MONTH), person.getBirthDate().get(Calendar.MONTH), "was here !!!!");
        Assertions.assertEquals(expectedDate.get(Calendar.DAY_OF_MONTH), person.getBirthDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void createPatient() {
        Patient patient = new BuildEntityDBFromJson().createPatient(json);

        Assertions.assertNotNull(patient);
        Assertions.assertEquals(null, patient.getDisability());
        Assertions.assertEquals(InsuranceProvider.AETNA, patient.getInsuranceProvider());
    }

    @Test
    public void createShift() {
        Shift shift = new BuildEntityDBFromJson().createShift(json);

        Assertions.assertNotNull(shift);
        Assertions.assertEquals(70, shift.getPrice());
        Assertions.assertEquals("feel bad", shift.getReason());
        Calendar expectedScheduling = Calendar.getInstance();
        expectedScheduling.set(2024, Calendar.AUGUST, 25, 14, 30);
        Assertions.assertEquals(expectedScheduling.get(Calendar.YEAR), shift.getScheduling().get(Calendar.YEAR));
        Assertions.assertEquals(expectedScheduling.get(Calendar.MONTH), shift.getScheduling().get(Calendar.MONTH));
        Assertions.assertEquals(expectedScheduling.get(Calendar.DAY_OF_MONTH), shift.getScheduling().get(Calendar.DAY_OF_MONTH));
        Assertions.assertEquals(expectedScheduling.get(Calendar.HOUR_OF_DAY), shift.getScheduling().get(Calendar.HOUR_OF_DAY));
        Assertions.assertEquals(expectedScheduling.get(Calendar.MINUTE), shift.getScheduling().get(Calendar.MINUTE));
    }
}
