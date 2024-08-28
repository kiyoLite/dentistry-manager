/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Persistence.DAO.Implementation.ShiftDAOImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author soyky
 */
@WebServlet(name = "SvDeleteShift", urlPatterns = {"/SvDeleteShift"})
public class SvDeleteShift extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String unfformatedId = request.getReader().readLine();
        long shiftId = Long.parseLong(unfformatedId);
        ShiftDAOImp shiftDAO = new ShiftDAOImp();
        boolean wasDeleted = shiftDAO.deleteById(shiftId);
        if (wasDeleted) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }//

}
