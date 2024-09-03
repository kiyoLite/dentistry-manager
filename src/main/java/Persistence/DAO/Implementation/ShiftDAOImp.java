/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence.DAO.Implementation;

import Logic.BuilderFilter;
import Persistence.DAO.Interface.ShiftDAO;
import Persistence.Entities.Person;
import Persistence.Entities.Patient;
import Persistence.Entities.Shift;
import Persistence.Entities.Dentist;
import Persistence.HibernateUtil;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import Persistence.Enums.FilterType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import org.hibernate.query.Query;

/**
 *
 * @author soyky
 */
public class ShiftDAOImp implements ShiftDAO {

    @Override
    public Shift create(Shift entity) {
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
    public List<Shift> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Shift getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Shift entity = session.find(Shift.class, id);
        session.close();
        return entity;
    }

    @Override
    public Shift update(Shift entity) {
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
        Shift entity = getById(entityId);
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

    public List<Object[]> getShiftsForTable(String search, int limit, int curId, boolean isNextPage, FilterType filterType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Shift> root = criteria.from(Shift.class);
        Join<Shift, Dentist> dentistData = root.join("dentist", JoinType.INNER);
        Join<Shift, Person> dentistPersonalData = dentistData.join("personalData", JoinType.INNER);
        Join<Shift, Patient> patientData = root.join("patient", JoinType.INNER);
        Join<Patient, Person> patientPersonalData = patientData.join("personalData", JoinType.INNER);
        Predicate filter = new BuilderFilter(
                builder,
                filterType == FilterType.PATIENTS
                        ? patientPersonalData
                        : filterType == FilterType.DENTIST
                                ? dentistPersonalData
                                : root,
                search).createFilter(filterType);
        Predicate filterPageDirection;

        if (isNextPage) {
            filterPageDirection = builder.greaterThan(root.get("id"), curId);
            criteria.orderBy(builder.asc(root.get("id")));
        } else {
            filterPageDirection = builder.lessThan(root.get("id"), curId);
            criteria.orderBy(builder.desc(root.get("id")));
        }

        criteria.multiselect(
                root.get("id"),
                root.get("price"),
                root.get("scheduling"),
                dentistPersonalData.get("firstName"),
                patientPersonalData.get("firstName"),
                patientPersonalData.get("email")
        );
        criteria.where(builder.and(filter, filterPageDirection));

        Query query = session.createQuery(criteria);
        query.setMaxResults(limit);
        List<Object[]> shifts = query.list();
        session.close();
        return shifts;

    }

}
