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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author soyky
 */
public class LoginDAOImp implements LoginDAO {

    @Override
    public boolean existLogin(String userName, String Password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery criteria = builder.createQuery();
        Root<Login> root = criteria.from(Login.class);
        Predicate userNameFilter = builder.equal(root.get("userName"), userName);
        Predicate passwordFilter = builder.equal(root.get("password"), Password);
        criteria.select(root).where(builder.and(userNameFilter, passwordFilter));

        Query query = session.createQuery(criteria);
        int quantityLogins = query.list().size();
        return quantityLogins >= 1;

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
        Login entity = getById(entityId);
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
