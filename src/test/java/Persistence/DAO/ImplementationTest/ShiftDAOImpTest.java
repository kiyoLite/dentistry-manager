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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
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
        Calendar referenceDate = Calendar.getInstance();
        referenceDate.add(Calendar.DAY_OF_MONTH, 3);
        shiftDB.setScheduling(referenceDate);
        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        shiftDAO.create(shiftDB);

        LocalDate now = LocalDate.now();
        List<Object[]> registers = shiftDAO.getShiftsForTable(now.toString(), 5, 0, true, FilterType.SCHEDULING);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Stream<LocalDate> dates = registers.stream().map(curElement -> {
            LocalDate registerScheduling = LocalDate.parse((String) curElement[2], formatter);
            return registerScheduling;
        });
        boolean areAllDatesAfter = dates.allMatch(curElement -> curElement.isAfter(now) || curElement.isEqual(now));
        Assertions.assertTrue(registers.size() >= 1);
        Assertions.assertTrue(areAllDatesAfter);

    }

    @Test
    public void getPreviousAndNextShift() {
        Calendar utilDate = Calendar.getInstance();
        utilDate.add(Calendar.MINUTE, 3);
        shiftDB.setScheduling(utilDate);
        shiftDB.setReason("proof shift after now");
        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        shiftDAO.create(shiftDB);
        utilDate.add(Calendar.MINUTE, -6);
        shiftDB.setScheduling(utilDate);
        shiftDB.setReason("proof shift before now");
        shiftDAO.create(shiftDB);

        Calendar now = Calendar.getInstance();
        List<Shift> shifts = shiftDAO.getPreviousAndNextShift();
        Shift nextShift = shifts.get(0);
        Shift previousShift = shifts.get(1);
        //remove one minute because whilte shift is generating the getInstance changes
        utilDate.add(Calendar.MINUTE, -1);
        boolean expectValueNS = nextShift == null || nextShift.getScheduling().after(now);
        boolean expectValuePS = previousShift == null || previousShift.getScheduling().before(now);
        Assertions.assertTrue(expectValueNS);
        Assertions.assertTrue(expectValuePS);
        Assertions.assertTrue(shifts.size() <= 2);

    }

}
