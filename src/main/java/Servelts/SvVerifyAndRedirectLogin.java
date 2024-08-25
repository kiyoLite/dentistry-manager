/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelts;

import Persistence.DAO.Implementation.LoginDAOImp;
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
@WebServlet(name = "SvVerifyAndRedirectLogin", urlPatterns = {"/SvVerifyAndRedirectLogin"})
public class SvVerifyAndRedirectLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject requestJson = new JSONObject(request.getReader().readLine());
        String userName = requestJson.getString("userName");
        String password = requestJson.getString("password");
        LoginDAOImp loginCrud = new LoginDAOImp();
        boolean existLogin = loginCrud.existLogin(userName, password);
        if (existLogin) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("urlHomePage");
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
