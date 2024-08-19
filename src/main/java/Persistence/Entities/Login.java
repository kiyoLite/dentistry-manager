/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.Entities;

import Persistence.DAO.Interface.DAOCRUD;

/**
 *
 * @author soyky
 */
public class Login implements EntityDB{
    private long id;
    private String userName;
    private String password;
    
    public Login(){}
    public Login (String userName , String password){
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    
}
