/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistence.DAO.Interface;
import Persistence.Entities.EntityDB;
import java.util.List;

/**
 *
 * @author soyky
 */
public interface DAOCRUD<T extends EntityDB> {
    
    public T create(T entity);
    public List<T> getAll ();
//    public T getById(long enittyId);
    public T update(T entity);
    public boolean delete(long entityId);
    
}