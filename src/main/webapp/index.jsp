<%-- 
    Document   : index
    Created on : Aug 19, 2024, 4:40:04 PM
    Author     : soyky
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Persistence.DAO.Implementation.PersonDAOImp" %>
<%@page import="java.util.Calendar" %>
<%@page import="Persistence.Entities.Person" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% Person person = new Person("kiyo","dev",Calendar.getInstance(),"kiyodev@gmail.com");
            PersonDAOImp Crud = new PersonDAOImp();
            Crud.create(person);
        %>
        <p> the entity was created succesfully </p>
        
    </body>
</html>
