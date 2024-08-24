/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.PatientDAOImp;
import Persistence.DAO.Implementation.PersonDAOImp;
import Persistence.Entities.Patient;
import Persistence.Entities.Person;
import Persistence.Enums.InsuranceProvider;
import java.util.Calendar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class PatientDAOImpTest {

    Patient patientDB;
    Person personDB;

    @BeforeEach
    public void setUp() {
        personDB = new Person("name1", "name2", Calendar.getInstance(), "email");
        patientDB = new Patient("disability", InsuranceProvider.HUMANA, personDB);

    }

    @AfterEach
    public void tearDown() {
        patientDB = null;
        personDB = null;
    }

    @Test
    public void create() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);

        Assertions.assertEquals(0, patientDB.getId());
        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);
        Assertions.assertNotEquals(0, personDB.getId(), "id have to be different to long default value");
    }

    @Test
    public void getById() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);

        Assertions.assertEquals(0, patientDB.getId());
        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);
        Patient recoveredPatient = crudPatient.getById(patientDB.getId());
        Assertions.assertEquals(patientDB.getDisability(), recoveredPatient.getDisability());
        Assertions.assertEquals(patientDB.getInsuranceProvider(), recoveredPatient.getInsuranceProvider());
        Assertions.assertEquals(patientDB.getPersonalData().getId(), recoveredPatient.getPersonalData().getId());
    }

    @Test
    public void deleteById() {
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);
        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);
        long patientId = patientDB.getId();
        boolean wasDeleted = crudPatient.deleteById(patientId);
        Assertions.assertTrue(wasDeleted);
        Patient deletedPatient = crudPatient.getById(patientId);
        Assertions.assertNull(deletedPatient);
    }

}
