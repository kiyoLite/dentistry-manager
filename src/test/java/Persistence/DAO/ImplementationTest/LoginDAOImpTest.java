/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.LoginDAOImp;
import org.junit.jupiter.api.Test;
import Persistence.Entities.Login;
import Persistence.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author soyky
 */
public class LoginDAOImpTest {
    @Test
    public void create(){
        Login loginDB = new Login("userProof","passwordProof");
        LoginDAOImp crud = new LoginDAOImp(); 
        crud.create(loginDB);
        Assertions.assertNotEquals(0, loginDB.getId(),"id have to be different to long default value");  
    }
}
