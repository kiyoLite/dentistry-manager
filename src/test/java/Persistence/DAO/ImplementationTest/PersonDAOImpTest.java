/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.PersonDAOImp;
import Persistence.Entities.Person;
import java.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class PersonDAOImpTest {
    @Test
    public void create() {
        Person personDB = new Person("name1", "name2", Calendar.getInstance(), "email");
        Assertions.assertEquals(0, personDB.getId());
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);
        Assertions.assertNotEquals(0, personDB.getId(), "id have to be different to long default value");
    }
}
