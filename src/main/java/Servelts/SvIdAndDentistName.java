/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Logic.JSONBuilder;
import Persistence.DAO.Implementation.DentistDAOImp;
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
@WebServlet(name = "SvIdAndDentistName", urlPatterns = {"/SvIdAndDentistName"})
public class SvIdAndDentistName extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DentistDAOImp dentistDAO = new DentistDAOImp();
        List<Object[]> dentistNameAndId = dentistDAO.getAllIdAndDentistName();
        JSONBuilder jsonBuilder = new JSONBuilder();
        String[] fieldsNames = new String[]{"Id,Name"};
        String genericKey = "register";
        JSONObject responseJSON = jsonBuilder.createFromListGenericsObjects(dentistNameAndId, fieldsNames, genericKey);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseJSON.toString());

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
