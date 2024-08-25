/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.ImplementationTest;

import Persistence.DAO.Implementation.LoginDAOImp;
import org.junit.jupiter.api.Test;
import Persistence.Entities.Login;
import javax.validation.constraints.AssertFalse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author soyky
 */
public class LoginDAOImpTest {

    Login loginDB;

    @BeforeEach
    public void setUp() {
        loginDB = new Login("userProof", "passwordProof");

    }

    @AfterEach
    public void tearDown() {
        loginDB = null;
    }

    @Test
    public void create() {
        Assertions.assertEquals(0, loginDB.getId());
        LoginDAOImp crud = new LoginDAOImp();
        crud.create(loginDB);
        Assertions.assertNotEquals(0, loginDB.getId(), "id have to be different to long default value");
    }

    @Test
    public void getById() {
        LoginDAOImp crudLogin = new LoginDAOImp();
        crudLogin.create(loginDB);
        Login recoveredLogin = crudLogin.getById(loginDB.getId());
        Assertions.assertEquals(loginDB.getUserName(), recoveredLogin.getUserName());
        Assertions.assertEquals(loginDB.getPassword(), recoveredLogin.getPassword());
    }

    @Test
    public void deleteById() {
        LoginDAOImp crudLogin = new LoginDAOImp();
        crudLogin.create(loginDB);
        long loginId = loginDB.getId();
        boolean wasDeleted = crudLogin.deleteById(loginId);
        Assertions.assertTrue(wasDeleted);
        Login deletedLogin = crudLogin.getById(loginId);
        Assertions.assertNull(deletedLogin);
    }

    @Test
    public void existLogin(){
        LoginDAOImp crudLogin = new LoginDAOImp();
        crudLogin.create(loginDB);
        boolean existLogin = crudLogin.existLogin(loginDB.getUserName(), loginDB.getPassword());
        Assertions.assertTrue(existLogin);
    }
}
