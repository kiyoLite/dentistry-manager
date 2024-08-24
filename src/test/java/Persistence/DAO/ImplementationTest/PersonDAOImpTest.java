/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.PersonDAOImp;
import Persistence.Entities.Person;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class PersonDAOImpTest {
    Person personDB;

    @BeforeEach
    public void setUp() {
        personDB = new Person("name1", "name2", Calendar.getInstance(), "email");

    }
    @AfterEach
    public void tearDown() {
        personDB = null;
    }

    @Test
    public void create() {
        Assertions.assertEquals(0, personDB.getId());
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);
        Assertions.assertNotEquals(0, personDB.getId(), "id have to be different to long default value");
    }

    @Test
    public void getById() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);
        Person recoveredPerson = crudPerson.getById(personDB.getId());
        Assertions.assertEquals(personDB.getFirstName(), recoveredPerson.getFirstName());
        Assertions.assertEquals(personDB.getLastName(), recoveredPerson.getLastName());
        SimpleDateFormat formatDate  = new SimpleDateFormat("yyyy-MM-dd");
        String birtDateRecoveredPerson = formatDate.format(recoveredPerson.getBirthDate().getTime());
        String birtDatePersonDB = formatDate.format(personDB.getBirthDate().getTime());
        Assertions.assertEquals(birtDatePersonDB,birtDateRecoveredPerson);
        Assertions.assertEquals(personDB.getEmail(), recoveredPerson.getEmail());
    }
    
    @Test
    public void deleteById(){
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);
        long personId = personDB.getId();
        boolean WasDeleted = crudPerson.deleteById(personId);
        Assertions.assertTrue(WasDeleted);
        Person deletedPerson = crudPerson.getById(personId);
        Assertions.assertNull(deletedPerson);
    }
}
