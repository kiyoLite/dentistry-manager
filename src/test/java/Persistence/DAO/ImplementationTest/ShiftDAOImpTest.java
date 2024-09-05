/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.ImplementationTest;

import Logic.JSONBuilder;
import Persistence.DAO.Implementation.DentistDAOImp;
import Persistence.DAO.Implementation.PersonDAOImp;
import Persistence.DAO.Implementation.PatientDAOImp;
import Persistence.DAO.Implementation.ScheduleDAOImp;
import Persistence.DAO.Implementation.ShiftDAOImp;
import Persistence.Entities.Dentist;
import Persistence.Entities.Person;
import Persistence.Entities.Patient;
import Persistence.Entities.Shift;
import Persistence.Entities.Schedule;
import Persistence.Enums.FilterType;
import Persistence.Enums.InsuranceProvider;
import Persistence.Enums.Specialization;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class ShiftDAOImpTest {

    Person person1DB;
    Person person2DB;
    Patient patientDB;
    Schedule scheduleDB;
    Dentist dentistDB;
    Shift shiftDB;

    @BeforeEach
    public void setUp() {
        person1DB = new Person("name1", "name2", Calendar.getInstance(), "email");
        person2DB = new Person("name1", "name2", Calendar.getInstance(), "email");
        patientDB = new Patient("disability", InsuranceProvider.AETNA, person1DB);
        scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        dentistDB = new Dentist(Specialization.MAXILLOFACIAL, scheduleDB, person2DB);
        shiftDB = new Shift(patientDB, dentistDB, Calendar.getInstance(), "reason", 0);
    }

    @AfterEach
    public void tearDown() {
        person1DB = null;
        person2DB = null;
        patientDB = null;
        scheduleDB = null;
        dentistDB = null;
        shiftDB = null;
    }

    @Test
    public void createManually() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(person1DB);
        crudPerson.create(person2DB);

        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);

        ScheduleDAOImp crudSchedule = new ScheduleDAOImp();
        crudSchedule.create(scheduleDB);

        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);

        Assertions.assertEquals(0, shiftDB.getId());
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);
        Assertions.assertNotEquals(0, shiftDB.getId(), "id have to be different to long default value");

    }

    @Test
    public void createAutomatically() {
        Assertions.assertEquals(0, shiftDB.getId());
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);
        Assertions.assertNotEquals(0, shiftDB.getId(), "id have to be different to long default value");
    }

    @Test
    public void getById() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(person1DB);
        crudPerson.create(person2DB);

        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);

        ScheduleDAOImp crudSchedule = new ScheduleDAOImp();
        crudSchedule.create(scheduleDB);

        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);

        Assertions.assertEquals(0, shiftDB.getId());
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);

        Shift recoveredShift = crudShift.getById(shiftDB.getId());
        Assertions.assertEquals(shiftDB.getPatient().getId(), recoveredShift.getPatient().getId());
        Assertions.assertEquals(shiftDB.getDentist().getId(), recoveredShift.getDentist().getId());
        Assertions.assertEquals(shiftDB.getScheduling(), recoveredShift.getScheduling());
        Assertions.assertEquals(shiftDB.getReason(), recoveredShift.getReason());
        Assertions.assertEquals(shiftDB.getPrice(), recoveredShift.getPrice());
    }

    @Test
    public void deleteById() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(person1DB);
        crudPerson.create(person2DB);
        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);
        ScheduleDAOImp crudSchedule = new ScheduleDAOImp();
        crudSchedule.create(scheduleDB);
        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);
        Assertions.assertEquals(0, shiftDB.getId());
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);

        long shiftId = shiftDB.getId();
        boolean wasDeleted = crudShift.deleteById(shiftId);
        Assertions.assertTrue(wasDeleted);
        Shift deletedShift = crudShift.getById(shiftId);
        Assertions.assertNull(deletedShift);

    }

    @Test
    public void update() {
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);
        shiftDB.setPrice(200);
        shiftDB.getPatient().setDisability("another disability");
        shiftDB.getPatient().getPersonalData().setFirstName("another name");
        shiftDB.getDentist().setDentistEspecialization(Specialization.ORAL);
        crudShift.update(shiftDB);

    }

    @Test
    public void getShiftsForTableDefaultFilter() {
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);
        List<Object[]> shifts = crudShift.getShiftsForTable("", 5, 0, true, FilterType.PREDETERMINED);
        String[] fieldsName = new String[]{"id", "price", "scheduling", "dentistName", "patientName", "email"};
        Assertions.assertTrue(shifts.size() >= 1, "always should return at least one record");
        Assertions.assertTrue(shifts.get(0).length == 6, "expected retorned fields from DB");

    }

    @Test
    public void getShiftsForTablePatientFilter() {
        String expectPatientName = "testName";
        shiftDB.getPatient().getPersonalData().setFirstName(expectPatientName);
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);

        List<Object[]> shifts = crudShift.getShiftsForTable("testName", 5, 0, true, FilterType.PATIENTS);
        Assertions.assertTrue(shifts.size() == 1, "always should return  one record");
        Object[] firstRegister = shifts.get(0);
        String patientName = (String) firstRegister[4];
        Assertions.assertTrue(expectPatientName.equals(patientName));
    }

    @Test
    public void getShiftsForTableDentistFilter() {
        String expectDentistName = "testName";
        shiftDB.getDentist().getPersonalData().setFirstName(expectDentistName);
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);

        List<Object[]> shifts = crudShift.getShiftsForTable("testName", 5, 0, true, FilterType.DENTIST);
        Assertions.assertTrue(shifts.size() == 1, "always should return  one record");
        Object[] firstRegister = shifts.get(0);
        String DentistName = (String) firstRegister[3];
        Assertions.assertTrue(expectDentistName.equals(DentistName));
    }

    @Test
    public void getShiftsForTablePriceFilter() {
        shiftDB.setPrice(300);
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);

        List<Object[]> shifts = crudShift.getShiftsForTable("300-500", 5, 0, true, FilterType.PRICE);
        Assertions.assertTrue(shifts.size() >= 1);
        Stream<Integer> priceShifts = shifts.stream().map(curShift -> (int) curShift[1]);
        boolean ArePriceShiftsInRange = priceShifts.allMatch(price -> price >= 300 && price <= 500);
        Assertions.assertTrue(ArePriceShiftsInRange);
    }

    @Test
    public void getShiftsForTableAfterSchedulingFilter() {
        Calendar referenceCalendar = Calendar.getInstance();
        referenceCalendar.set(2024, 8, 15);
        shiftDB.setScheduling(referenceCalendar);
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);
        
        // set a new scheduling for created shift , remember that Calendar represent month in range 0 -11  so new scheduling and search parameter have the same date
        List<Object[]> shifts = crudShift.getShiftsForTable("2024-09-15", 5, 0, true, FilterType.SCHEDULING);
        Stream<Calendar> schedulingShifts = shifts.stream().map(curShift -> (Calendar) curShift[2]);
        boolean AreSheculingShiftsInRange = schedulingShifts.allMatch( curScheduling -> curScheduling.after(referenceCalendar));
        Assertions.assertTrue(AreSheculingShiftsInRange);
    }

}
