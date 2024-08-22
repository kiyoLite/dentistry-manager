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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class ShiftDAOImpTest {

    @Test
    public void create() {
        Person person1DB = new Person("name1", "name2", Calendar.getInstance(), "email");
        Person person2DB = new Person("name1", "name2", Calendar.getInstance(), "email");
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(person1DB);
        crudPerson.create(person2DB);

        Patient patientDB = new Patient("disability", InsuranceProvider.AETNA, person1DB);
        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);

        Schedule scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        ScheduleDAOImp crudSchedule = new ScheduleDAOImp();
        crudSchedule.create(scheduleDB);

        Dentist dentistDB = new Dentist(Specialization.MAXILLOFACIAL, scheduleDB, person2DB);
        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);
        
        Shift shiftDB = new Shift(patientDB, dentistDB, Calendar.getInstance(), "reason", 0);
        Assertions.assertEquals(0, shiftDB.getId());
        ShiftDAOImp crudShift = new ShiftDAOImp();
        crudShift.create(shiftDB);
        Assertions.assertNotEquals(0, shiftDB.getId(), "id have to be different to long default value");
        
        

    }
}
