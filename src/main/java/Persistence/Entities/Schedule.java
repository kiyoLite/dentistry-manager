/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;

import java.util.Calendar;

/**
 *
 * @author soyky
 */
public class Schedule implements EntityDB{
    private long id;
    private Calendar startSchedule ;
    private Calendar endScehdule;
    public Schedule(){}
    public Schedule(Calendar startSchedule, Calendar endSchedule){
        this.startSchedule = startSchedule;
        this.endScehdule = endSchedule;
    }

    public long getId() {
        return id;
    }

    public Calendar getStartSchedule() {
        return startSchedule;
    }

    public Calendar getEndScehdule() {
        return endScehdule;
    }

    public void setStartSchedule(Calendar startSchedule) {
        this.startSchedule = startSchedule;
    }

    public void setEndScehdule(Calendar endScehdule) {
        this.endScehdule = endScehdule;
    }
    
    
}
