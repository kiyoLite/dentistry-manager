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
        return null;
    }

    @Override
    public boolean delete(long entityId) {
        return true;
    }

}
