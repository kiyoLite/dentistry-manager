/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author soyky
 */
@Entity
@Table(name = "people")
public class Person implements EntityDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Calendar birthDate;
    private String email;

    public Person() {
    }

    ;
    public Person(String firstName, String LastName, Calendar birthDate, String email) {
        this.firstName = firstName;
        this.lastName = LastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    ;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    } 

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
