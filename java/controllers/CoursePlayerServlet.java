package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseModuleMaterialDao;
import models.CourseModuleMaterial;

@WebServlet("/CoursePlayerServlet")
public class CoursePlayerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the material ID from the request
    	response.setContentType("video/mp4");
        String materialID = request.getParameter("materialID");

        System.out.println("materialID" + materialID);
        // In a real application, you would use a data access object (DAO) to retrieve the course material
        CourseModuleMaterialDao materialDao = new CourseModuleMaterialDao(); // Instantiate your DAO

        try {
            // Query for the specific course material based on materialID
            CourseModuleMaterial material = materialDao.getMaterialByID(materialID);



            // Set the material as an attribute in the request so that the JSP can access it
            request.setAttribute("courseMaterial", material);
            request.setAttribute("videoFilePath", material.getFilePath());
            // Forward the request to the JSP for rendering
            //response.sendRedirect("admin-pages/admin-courses.html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin-pages/student-course-player.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Handle any exceptions that might occur during the database query and respond with an error message if needed
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving course material.");
        }
    }
}

