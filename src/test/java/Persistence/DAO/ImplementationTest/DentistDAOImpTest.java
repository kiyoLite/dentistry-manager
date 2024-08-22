package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.DentistDAOImp;
import Persistence.DAO.Implementation.PersonDAOImp;
import Persistence.DAO.Implementation.ScheduleDAOImp;
import Persistence.Entities.Dentist;
import Persistence.Entities.Person;
import Persistence.Entities.Schedule;
import Persistence.Enums.Specialization;
import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    Schedule scheduleDB;
    Person personDB;
    Dentist dentistDB;

    @BeforeEach
    public void setUp() {
        scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        personDB = new Person("name1", "name2", Calendar.getInstance(), "email");
        dentistDB = new Dentist(Specialization.MAXILLOFACIAL, scheduleDB, personDB);

    }

    @AfterEach
    public void tearDown() {
        scheduleDB = null;
        personDB = null;
        dentistDB = null;
    }

    @Test
    public void create() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);

        ScheduleDAOImp crudSchedule = new ScheduleDAOImp();
        crudSchedule.create(scheduleDB);

        Assertions.assertEquals(0, dentistDB.getId());
        DentistDAOImp crudDentist = new DentistDAOImp();
        crudDentist.create(dentistDB);
        Assertions.assertNotEquals(0, dentistDB.getId(), "id have to be different to long default value");

    }
}
