
import Persistence.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author soyky
 */
public class other {
    
    @Test
    public void createSession(){
        Session session = HibernateUtil.getSessionFactory().openSession();
    }
}
