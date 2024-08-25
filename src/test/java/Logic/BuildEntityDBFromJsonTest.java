/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Persistence.Entities.Person;
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
    public void setUp(){
         String jsonString =  "{"
                + "\"dentist\": \"1\","
                + "\"patientBirthDate\": \"2007-04-19\","
                + "\"patientDisability\": \"\","
                + "\"patientEmail\": \"kiyodeveloper@gmail.com\","
                + "\"PatientInsuranceProvider\": \"Sanitas\","
                + "\"patientFirstName\": \"kiyo\","
                + "\"patientLastName\": \"dev\","
                + "\"shiftDate\": \"2024-08-25\","
                + "\"shiftPrice\": \"70\","
                + "\"shiftReason\": \"fill bad\","
                + "\"shiftTime\": \"14:30\""
                + "}";
         json = new JSONObject(jsonString);
    }
    
    @AfterEach
    public void tearDown(){
        json = null;
    }
    
    
    @Test
     public void createPersonPatient(){
        BuildEntityDBFromJson build = new BuildEntityDBFromJson();
        Person person = build.createPersonPatient(json);

        // Verificar que el objeto Person no sea nulo
        Assertions.assertNotNull(person);

        // Verificar que los valores sean correctos
        Assertions.assertEquals("kiyo", person.getFirstName());
        Assertions.assertEquals("dev", person.getLastName());

        // Verificar la fecha de nacimiento
        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2007, Calendar.APRIL, 19);
        Assertions.assertEquals(expectedDate.get(Calendar.YEAR), person.getBirthDate().get(Calendar.YEAR));
        Assertions.assertEquals(expectedDate.get(Calendar.MONTH), person.getBirthDate().get(Calendar.MONTH),"was here !!!!");
        Assertions.assertEquals(expectedDate.get(Calendar.DAY_OF_MONTH), person.getBirthDate().get(Calendar.DAY_OF_MONTH));
     }
}
