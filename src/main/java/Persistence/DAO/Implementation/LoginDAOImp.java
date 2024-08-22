/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.Implementation;

import Persistence.DAO.Interface.LoginDAO;
import Persistence.Entities.Login;
import Persistence.Entities.Shift;
import Persistence.HibernateUtil;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;

/**
 *
 * @author soyky
 */
public class LoginDAOImp implements LoginDAO {

    @Override
    public boolean existLogin(String userName, String Password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody 
    }

    @Override
    public Login create(Login entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return entity;
        }
    }

    @Override
    public List<Login> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Login getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Login entity = session.find(Login.class, id);
        session.close();
        return entity;
    }

    @Override
    public Login update(Login entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(long entityId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
