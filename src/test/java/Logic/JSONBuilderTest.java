package Logic;

import Persistence.DAO.Implementation.DentistDAOImp;
import Persistence.Entities.Dentist;
import Persistence.Entities.Person;
import Persistence.Entities.Schedule;
import Persistence.Enums.Specialization;
import java.util.Calendar;
import java.util.List;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
