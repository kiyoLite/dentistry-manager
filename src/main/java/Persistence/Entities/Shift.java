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
public class Shift implements EntityDB{
    private long id;
    private Patient patinet;
    private Dentist dentist;
    private Calendar scheduling;
    private String reason;
    private int price;
    
    public Shift(){}
    public Shift( Patient patient, Dentist dentist, Calendar scheduling, String reason, int price){
        this.patinet = patient;
        this.dentist = dentist;
        this.scheduling = scheduling;
        this.reason = reason;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public Patient getPatinet() {
        return patinet;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public Calendar getScheduling() {
        return scheduling;
    }

    public String getReason() {
        return reason;
    }

    public int getPrice() {
        return price;
    }

    public void setPatinet(Patient patinet) {
        this.patinet = patinet;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public void setScheduling(Calendar scheduling) {
        this.scheduling = scheduling;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
