package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CourseDao;
import models.Course;

@WebServlet("/CourseApiServlet")
public class CourseApiServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //CourseDao courseDao = new CourseDao(DatabaseUtil.getConnection());
        
		try {
			List<Course> courses = CourseDao.getAllCourses();
			String jsonResponse = convertCoursesToJSON(courses);
			
			
			response.setContentType("application/json");
			
			// JSON response
			response.getWriter().write(jsonResponse);
			
		} catch (Exception e) {

			e.printStackTrace();
		}


    }

    private String convertCoursesToJSON(List<Course> courses) {
    	Gson gson = new Gson();

        // Convert the list of courses to JSON
        String json = gson.toJson(courses);

        return json;

    }
}

