/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;
import Persistence.Enums.InsuranceProvider;

/**
 *
 * @author soyky
 */
public class Patient implements EntityDB{
   private long id;
   private String disability;
   private InsuranceProvider insuranceProvider;
   private Person personalData;
   
   public Patient(){};

    public Patient(String disability, InsuranceProvider insuranceProvider, Person personalData) {
        this.disability = disability;
        this.insuranceProvider = insuranceProvider;
        this.personalData = personalData;
    }

    public long getId() {
        return id;
    }

    public String getDisability() {
        return disability;
    }

    public InsuranceProvider getInsuranceProvider() {
        return insuranceProvider;
    }

    public Person getPersonalData() {
        return personalData;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public void setInsuranceProvider(InsuranceProvider insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public void setPersonalData(Person personalData) {
        this.personalData = personalData;
    }
   
   
   
}
