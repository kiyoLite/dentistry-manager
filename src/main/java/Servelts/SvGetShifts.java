/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Logic.JSONBuilder;
import Persistence.DAO.Implementation.ShiftDAOImp;
import Persistence.Enums.FilterType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Source;
import org.json.JSONObject;

/**
 *
 * @author soyky
 */
@WebServlet(name = "SvGetShifts", urlPatterns = {"/SvGetShifts"})
public class SvGetShifts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int limit = Integer.parseInt(request.getParameter("quantity"));
            int referenceId = Integer.parseInt(request.getParameter("startFrom"));
            boolean isNextPage = Boolean.valueOf(request.getParameter("isNextPage"));
            String search = request.getParameter("search");
            FilterType filter = FilterType.valueOf(request.getParameter("filter"));
            
            ShiftDAOImp shiftDAO = new ShiftDAOImp();
            List<Object[]> shifts = shiftDAO.getShiftsForTable(search, limit, referenceId, isNextPage, filter);
            String[] fieldsName = new String[]{"id","price","scheduling","dentistName","patientName","email"};
            String genericKey = "register";
            JSONObject responseJSON = new JSONBuilder().createFromListGenericsObjects(shifts, fieldsName, genericKey);
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseJSON.toString());
            
            
            

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

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
