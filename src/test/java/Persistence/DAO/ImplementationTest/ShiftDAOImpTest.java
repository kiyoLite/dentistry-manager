/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.ImplementationTest;

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
import Persistence.Enums.InsuranceProvider;
import Persistence.Enums.Specialization;
import java.util.Calendar;
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
    public void create() {
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
    public void getById(){
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
        Assertions.assertEquals(shiftDB.getPatinet().getId(), recoveredShift.getPatinet().getId());
        Assertions.assertEquals(shiftDB.getDentist().getId(), recoveredShift.getDentist().getId());
        Assertions.assertEquals(shiftDB.getScheduling(), recoveredShift.getScheduling());
        Assertions.assertEquals(shiftDB.getReason(), recoveredShift.getReason() );
        Assertions.assertEquals(shiftDB.getPrice(), recoveredShift.getPrice());
    }
}
