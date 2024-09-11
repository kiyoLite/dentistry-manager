/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistence.DAO.Interface;

import Persistence.Entities.Shift;
import Persistence.Enums.FilterType;
import java.util.List;

/**
 *
 * @author soyky
 */
public interface ShiftDAO extends DAOCRUD<Shift>{
     public List<Object[]> getShiftsForTable(String search, int limit, int curId, boolean isNextPage, FilterType filterType);
     public List<Shift> getShiftForTodayByDentist(long dentistId );
     public List<Shift> getPreviousAndNextShift();
}
