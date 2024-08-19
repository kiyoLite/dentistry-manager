/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistence.DAO.Interface;
import Persistence.Entities.Login;

/**
 *
 * @author soyky
 */
public interface LoginDAO extends DAOCRUD<Login>{
    public boolean existLogin(String userName, String Password);
}
