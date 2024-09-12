/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Logic.JSONBuilder;
import Persistence.DAO.Implementation.ShiftDAOImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Persistence.Entities.Shift;
import org.json.JSONObject;

/**
 *
 * @author soyky
 */
@WebServlet(name = "SvGetSingleShift", urlPatterns = {"/SvGetSingleShift"})
public class SvGetSingleShift extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String unformattedShiftId = request.getReader().readLine();
        long shiftId = Long.parseLong(unformattedShiftId);
        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        Shift shift = shiftDAO.getById(shiftId);
        JSONBuilder builder = new JSONBuilder();
        JSONObject responseJSON = builder.createFromShift(shift);
        
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
