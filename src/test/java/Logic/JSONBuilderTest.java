package Logic;

import Persistence.DAO.Implementation.DentistDAOImp;
import Persistence.Entities.Dentist;
import Persistence.Entities.Patient;
import Persistence.Entities.Person;
import Persistence.Entities.Schedule;
import Persistence.Entities.Shift;
import Persistence.Enums.InsuranceProvider;
import Persistence.Enums.Specialization;
import java.util.Calendar;
import java.util.List;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class JSONBuilderTest {

    @Test
    public void createFromGeneericObject() {
        Schedule scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        Person personDB = new Person("name1", "name2", Calendar.getInstance(), "email");
        Dentist dentistDB = new Dentist(Specialization.MAXILLOFACIAL, scheduleDB, personDB);
        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);

        List<Object[]> collectionObjects = crudDentist.getAllIdAndDentistName();
        Object[] GenericObject = collectionObjects.get(0);
        JSONBuilder builder = new JSONBuilder();
        JSONObject responseJSON = builder.createFromGenericObject(GenericObject, new String[]{"id", "name"});
        Assertions.assertTrue(responseJSON.length() == 2);
        String unformattedId = responseJSON.optString("id");
        long id = !unformattedId.equals("") ? Long.parseLong(unformattedId) : 0L;
        String name = responseJSON.optString("name");
        Assertions.assertNotEquals(0, id);
        Assertions.assertTrue(!"".equals(name));

    }

    @Test
    public void createFromListGenericsObjects() {
        Schedule scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        Person personDB = new Person("name1", "name2", Calendar.getInstance(), "email");
        Dentist dentistDB = new Dentist(Specialization.MAXILLOFACIAL, scheduleDB, personDB);
        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);

        List<Object[]> collectionObjects = crudDentist.getAllIdAndDentistName();
        JSONBuilder builder = new JSONBuilder();
        String genericKeyForRegisters = "registers";
        JSONObject responseJSON = builder.createFromListGenericsObjects(collectionObjects, new String[]{"id", "name"}, genericKeyForRegisters);
        JSONObject subJSON = responseJSON.getJSONObject(genericKeyForRegisters + 1);
        Assertions.assertTrue(subJSON.length() == 2);

    }

    @Test
    public void createFromShift() {
        Person personpatient = new Person("name1", "name2", Calendar.getInstance(), "email");
        Person personDentist = new Person("name1", "name2", Calendar.getInstance(), "email");
        Patient patient = new Patient("disability", InsuranceProvider.AETNA, personpatient);
        Schedule schedule = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        Dentist dentist = new Dentist(Specialization.MAXILLOFACIAL, schedule, personDentist);
        Shift shift = new Shift(patient, dentist, Calendar.getInstance(), "reason", 0);

        String[] expectJSONKeys = new String[]{
            "patientEps",
            "dentistId",
            "price",
            "patientFirstName",
            "scheduling",
            "patientEmail",
            "dentistName",
            "patientBirthDate",
            "shiftReason",
            "patientDisability",
            "patientLastName"};
        JSONBuilder builder = new JSONBuilder();
        JSONObject JSONFromShift = builder.createFromShift(shift);
        Object[] JSONkeys = JSONFromShift.keySet().toArray();
        Assertions.assertTrue(Arrays.equals(JSONkeys,expectJSONKeys));
        
    }
}
