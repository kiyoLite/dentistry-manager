package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.DentistDAOImp;
import Persistence.DAO.Implementation.PersonDAOImp;
import Persistence.DAO.Implementation.ScheduleDAOImp;
import Persistence.Entities.Dentist;
import Persistence.Entities.Person;
import Persistence.Entities.Schedule;
import Persistence.Enums.Specialization;
import java.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author soyky
 */
public class DentistDAOImpTest {

    @Test
    public void create() {
        Person personDB = new Person("name1", "name2", Calendar.getInstance(), "email");
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);

        Schedule scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        ScheduleDAOImp crudSchedule = new ScheduleDAOImp();
        crudSchedule.create(scheduleDB);

        Dentist dentistDB = new Dentist(Specialization.MAXILLOFACIAL, scheduleDB, personDB);
        Assertions.assertEquals(0, dentistDB.getId());
        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);
        Assertions.assertNotEquals(0, dentistDB.getId(), "id have to be different to long default value");
        

    }
}
