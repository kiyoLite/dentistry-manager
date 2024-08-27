/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.Implementation;

import Persistence.DAO.Interface.DentistDAO;
import Persistence.Entities.Dentist;
import Persistence.Entities.Shift;
import Persistence.HibernateUtil;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;

/**
 *
 * @author soyky
 */
public class DentistDAOImp implements DentistDAO {

    @Override
    public Dentist create(Dentist entity) {
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
    public List<Dentist> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Dentist getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Dentist entity = session.find(Dentist.class, id);
        session.close();
        return entity;
    }

    @Override
    public Dentist update(Dentist entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            return entity;
        } catch (PersistenceException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public boolean deleteById(long entityId) {
        Dentist entity = getById(entityId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

}
