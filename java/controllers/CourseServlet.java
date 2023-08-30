package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CourseDao;
import models.Course;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //CourseDao courseDao = new CourseDao(DatabaseUtil.getConnection());
        List<Course> courses = null;
		try {
			courses = CourseDao.getAllCourses();
		} catch (Exception e) {

			e.printStackTrace();
		}

        // Convert courses to JSON
        String jsonResponse = convertCoursesToJSON(courses);

        
        response.setContentType("application/json");

        
        response.getWriter().write(jsonResponse);
    }

    private String convertCoursesToJSON(List<Course> courses) {
    	Gson gson = new Gson();

        // Convert the list of courses to JSON
        String json = gson.toJson(courses);

        return json;

    }
}

