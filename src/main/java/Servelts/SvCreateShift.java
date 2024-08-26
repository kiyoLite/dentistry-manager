/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Logic.BuildEntityDBFromJson;
import Persistence.DAO.Implementation.ShiftDAOImp;
import Persistence.Entities.Shift;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvCreateShift", urlPatterns = {"/SvCreateShift"})
public class SvCreateShift extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject requestJson = new JSONObject(request.getReader().readLine());
        Shift shift = new BuildEntityDBFromJson().createShift(requestJson);
        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        shiftDAO.create(shift);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
