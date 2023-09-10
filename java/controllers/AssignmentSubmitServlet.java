package controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.AssignmentDao;
import dao.AssignmentSubmissionDao;
import models.AssignmentSubmission;
import models.User;


@WebServlet("/AssignmentSubmitServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 10 // 10MB
	)
public class AssignmentSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
    public AssignmentSubmitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String uploadDirectory = "C:\\Eclipse\\fileStore\\assignmentSubmission"; // Replace with the actual directory path

        
        // Ensure the directory exists; create it if necessary
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {

            Part filePart = request.getPart("file");

            String fileName = getFileName(filePart);

            String filePath = uploadDirectory + File.separator + fileName;
            filePart.write(filePath);

            int assignmentID = Integer.parseInt(request.getParameter("assignment-id"));
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");
            String userID = currentUser.getUserID();
            
            AssignmentSubmission submission = new AssignmentSubmission(assignmentID, userID, filePath);
            boolean success = AssignmentSubmissionDao.addAssignmentSubmission(submission);
            
            
            response.sendRedirect("studnent-pages/student-module.jsp");
        } catch (Exception e) {
            response.getWriter().write("File upload failed: " + e.getMessage());
        }
    }


    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
