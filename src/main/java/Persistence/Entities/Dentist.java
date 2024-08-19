/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;

import Persistence.Enums.Specialization;
import java.util.List;

/**
 *
 * @author soyky
 */
public class Dentist implements EntityDB{
    private long id;
    private Specialization dentistEspecialization;
    private List<Shift> shifts;
    private Schedule schedule;
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
