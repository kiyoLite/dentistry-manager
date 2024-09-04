/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import Persistence.DAO.Implementation.ShiftDAOImp;
import Persistence.Entities.Dentist;
import Persistence.Entities.Patient;
import Persistence.Entities.Person;
import Persistence.Entities.Schedule;
import Persistence.Entities.Shift;
import Persistence.Enums.InsuranceProvider;
import Persistence.Enums.Specialization;
import Persistence.HibernateUtil;
import java.util.Calendar;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

/**
 *
 * @author soyky
 */
public class other {

    @Test
    public void createSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    public void createShiftForToday() {
        Person person1DB = new Person("name1", "name2", Calendar.getInstance(), "email");
        Person person2DB = new Person("name1", "name2", Calendar.getInstance(), "email");
        Patient patientDB = new Patient("disability", InsuranceProvider.AETNA, person1DB);
        Schedule scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());
        Dentist dentistDB = new Dentist(Specialization.MAXILLOFACIAL, scheduleDB, person2DB);

        Calendar dateA = Calendar.getInstance();
        Shift shiftA = new Shift(patientDB, dentistDB, dateA, "reason", 0);

        Calendar dateB = Calendar.getInstance();
        dateB.set(Calendar.HOUR, 2);
        dateB.set(Calendar.AM_PM, Calendar.AM);
        Shift shiftB = new Shift(patientDB, dentistDB, dateB, "reason", 0);

        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        shiftDAO.create(shiftA);
        shiftDAO.create(shiftB);

    }
}
