package controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.AssignmentDao;

@WebServlet("/AssignmentFileUploadServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,    // 1 MB
    maxFileSize = 10 * 1024 * 1024,    // 10 MB
    maxRequestSize = 10 * 1024 * 1024  // 10 MB
)
public class AssignmentFileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uploadDirectory = "C:\\Eclipse\\fileStore"; // Replace with the actual directory path

        
        // Ensure the directory exists; create it if necessary
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            // Get the assignmentName and moduleID from request parameters
            String assignmentName = request.getParameter("assignmentName");
            
            // moudle id 
            int moduleID = Integer.parseInt(request.getParameter("get-module")); // oriignal for teacher pages
            
            System.out.println("");
            // Get the file part from the request
            Part filePart = request.getPart("file");

            // Get the file name
            String fileName = getFileName(filePart);

            // Write the file to the specified directory
            String filePath = uploadDirectory + File.separator + fileName;
            filePart.write(filePath);

            // store in database
            AssignmentDao assignementDao = new AssignmentDao();
            assignementDao.createAssignment(assignmentName, filePath, moduleID);
            
            // You can now use assignmentName and moduleID as needed
            response.getWriter().write("File " + fileName + " uploaded for assignment: " + assignmentName + " in module: " + moduleID);
        } catch (Exception e) {
            response.getWriter().write("File upload failed: " + e.getMessage());
        }
    }

    // Extracts the file name from a part's content-disposition header
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
}
