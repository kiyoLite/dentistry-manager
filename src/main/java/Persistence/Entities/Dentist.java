/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;

import Persistence.Enums.Specialization;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author soyky
 */
@Entity
@Table(name ="dentists")
public class Dentist implements EntityDB{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Specialization dentistEspecialization;
    @Transient
    private List<Shift> shifts;
    @OneToOne
    private Schedule schedule;
    @OneToOne
    private Person personalData;
    public Dentist(){}

    public Dentist(Specialization dentistEspecialization, List<Shift> shifts, Schedule schedule, Person personalData) {
        this.dentistEspecialization = dentistEspecialization;
        this.shifts = shifts;
        this.schedule = schedule;
        this.personalData = personalData;
    }
    
    
    public void setShiftForToday(){
        
    }

    public long getId() {
        return id;
    }

    public Specialization getDentistEspecialization() {
        return dentistEspecialization;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Person getPersonalData() {
        return personalData;
    }

    public void setDentistEspecialization(Specialization dentistEspecialization) {
        this.dentistEspecialization = dentistEspecialization;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setPersonalData(Person personalData) {
        this.personalData = personalData;
    }
    
    
    
    
    
    
}
