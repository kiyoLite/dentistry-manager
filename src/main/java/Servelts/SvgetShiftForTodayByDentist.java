/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Logic.JSONBuilder;
import Persistence.DAO.Implementation.ShiftDAOImp;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Persistence.Entities.Shift;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author soyky
 */
@WebServlet(name = "SvgetShiftForTodayByDentist", urlPatterns = {"/SvgetShiftForTodayByDentist"})
public class SvgetShiftForTodayByDentist extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String unformattedDentistId = request.getReader().readLine();
        long dentistId = Long.parseLong(unformattedDentistId);
        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        List<Shift> shiftsForToday = shiftDAO.getShiftForTodayByDentist(dentistId);
        JSONObject responseJSON = new JSONObject();
        JSONBuilder builder = new JSONBuilder();
        String genericKey = "register";
        for(int i = 0 ; i < shiftsForToday.size() ; i++){
            Shift shift = shiftsForToday.get(i);
            JSONObject shiftData = builder.createFromShift(shift);
            String key = genericKey + i;
            responseJSON.put(key, shiftData);
        }
        
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(responseJSON.toString());
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
