/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Logic.BuildEntityDBFromJson;
import Persistence.DAO.Implementation.ShiftDAOImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import Persistence.Entities.Shift;

/**
 *
 * @author soyky
 */
@WebServlet(name = "SvUpdateShift", urlPatterns = {"/SvUpdateShift"})
public class SvUpdateShift extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject requestJson = new JSONObject(request.getReader().readLine());
        int shiftId = requestJson.getInt("registerId");

        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        Shift oldShift = shiftDAO.getById(shiftId);

        long patientId = oldShift.getPatient().getId();
        long personPatientId = oldShift.getPatient().getPersonalData().getId();
        JSONObject shiftData = requestJson.getJSONObject("registerData");
        BuildEntityDBFromJson entityBuilder = new BuildEntityDBFromJson();
        Shift newShift = entityBuilder.createShift(shiftData);
        newShift.setId(shiftId);
        newShift.getPatient().setId(patientId);
        newShift.getPatient().getPersonalData().setId(personPatientId);

        shiftDAO.update(newShift);
        Shift updatedShift = shiftDAO.update(newShift);
        if (updatedShift != null) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
