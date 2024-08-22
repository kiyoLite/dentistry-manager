/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;
import Persistence.Enums.InsuranceProvider;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author soyky
 */
@Entity
@Table(name ="patients")
public class Patient implements EntityDB{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String disability;
   @Enumerated(EnumType.STRING)
   private InsuranceProvider insuranceProvider;
   @OneToOne
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
