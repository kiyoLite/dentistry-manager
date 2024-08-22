package Persistence.DAO.ImplementationTest;


import Persistence.DAO.Implementation.ScheduleDAOImp;
import Persistence.Entities.Schedule;
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

public class ScheduleDAOImpTest {
    @Test
     public void create(){
        Schedule scheduleDB = new Schedule(Calendar.getInstance(),Calendar.getInstance());
        ScheduleDAOImp crud = new ScheduleDAOImp();  
        crud.create(scheduleDB);
        Assertions.assertNotEquals(0, scheduleDB.getId(), "id have to be different to long default value");
    }
}
