/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Logic.JSONBuilder;
import Persistence.DAO.Implementation.ShiftDAOImp;
import Persistence.Entities.Shift;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author soyky
 */
@WebServlet(name = "SvgetCardsData", urlPatterns = {"/SvgetCardsData"})
public class SvgetCardsData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        List<Shift> shifts = shiftDAO.getPreviousAndNextShift();
        JSONBuilder builder = new JSONBuilder();
        JSONObject nextShift = builder.createFromShift(shifts.get(0));
        JSONObject previousShift = builder.createFromShift(shifts.get(1));
        JSONObject responseJSON = new JSONObject();
        responseJSON.put("nextShift", nextShift);
        responseJSON.put("previousShift", previousShift);
        
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
    }// </editor-fold>

}
