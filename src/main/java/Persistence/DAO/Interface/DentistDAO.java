/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistence.DAO.Interface;

import Persistence.Entities.Dentist;
import Persistence.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author soyky
 */
public interface DentistDAO extends DAOCRUD<Dentist> {

    public List<Object[]> getAllIdAndDentistName();
}
