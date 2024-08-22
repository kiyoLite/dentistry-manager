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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class PatientDAOImpTest {
    @Test
    public void create(){
        Person personDB = new Person("name1","name2",Calendar.getInstance(),"email");
        PersonDAOImp crudPerson = new PersonDAOImp();
        crudPerson.create(personDB);
        Patient patientDB = new Patient("disability", InsuranceProvider.HUMANA, personDB);
        Assertions.assertEquals(0, patientDB.getId());
        PatientDAOImp crudPatient = new PatientDAOImp();
        crudPatient.create(patientDB);
        Assertions.assertNotEquals(0, personDB.getId(), "id have to be different to long default value");
    }
    
    
    
}
