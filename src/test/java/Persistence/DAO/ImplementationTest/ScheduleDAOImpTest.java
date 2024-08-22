package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.ScheduleDAOImp;
import Persistence.Entities.Schedule;
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
public class ScheduleDAOImpTest {

    Schedule scheduleDB;

    @BeforeEach
    public void setUp() {
        Schedule scheduleDB = new Schedule(Calendar.getInstance(), Calendar.getInstance());

    }
    @AfterEach
    public void tearDown(){
        scheduleDB = null;
    }

    @Test
    public void create() {
        ScheduleDAOImp crud = new ScheduleDAOImp();
        crud.create(scheduleDB);
        Assertions.assertNotEquals(0, scheduleDB.getId(), "id have to be different to long default value");
    }
}
