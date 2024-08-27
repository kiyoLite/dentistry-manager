/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.Implementation;

import Persistence.DAO.Interface.PersonDAO;
import Persistence.Entities.Person;
import Persistence.HibernateUtil;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.PersistentObjectException;

/**
 *
 * @author soyky
 */
public class PersonDAOImp implements PersonDAO {

    public PersonDAOImp() {
    }

    @Override
    public Person create(Person person) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(person);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return person;
        }
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person update(Person entity) {
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
        Person entity = getById(entityId);
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

    @Override
    public Person getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Person entity = session.find(Person.class, id);
        session.close();
        return entity;
    }

}
