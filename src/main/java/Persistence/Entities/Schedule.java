/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author soyky
 */
@Entity
@Table(name = "schedules")
public class Schedule implements EntityDB{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startSchedule ;
    @Temporal(TemporalType.TIMESTAMP)
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
