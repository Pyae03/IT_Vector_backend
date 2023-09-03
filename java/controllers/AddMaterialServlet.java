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

import dao.CourseModuleMaterialDao;
import models.CourseModuleMaterial;

@WebServlet("/AddMaterialServlet")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,      // 1 MB
	    maxFileSize = 250 * 1024 * 1024,     // 250 MB
	    maxRequestSize = 250 * 1024 * 1024   // 250 MB
)
public class AddMaterialServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// change to your directory
        String uploadDirectory = "D:\\Computer University (Pyae Hein)\\Second Year (Second Semester)\\ProjectUploadFiles"; // Replace with the actual directory path

        
        System.out.println("enter servlet");
        // Ensure the directory exists; create it if necessary
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        System.out.println("enter file");
        try {
        
        	System.out.println("enter try catch");
            int moduleID = Integer.parseInt(request.getParameter("module-id")) ;
            String materialName = request.getParameter("material-name");
            String materialDescription = request.getParameter("material-description");
            
            // Get the file part from the request
            Part filePart = request.getPart("lecture-file");

            System.out.println("enter get FILE");
            // Get the file name
            String fileName = getFileName(filePart);

            // Write the file to the specified directory
            String filePath = uploadDirectory + File.separator + fileName;
            filePart.write(filePath);
            
            System.out.println("Uploaded File" + filePath);
            
            // save in database
            CourseModuleMaterial material = new CourseModuleMaterial( moduleID, materialName, materialDescription, filePath);
           
				boolean success = CourseModuleMaterialDao.addMaterial(material);
			if(success) {
	            System.out.println("Successed!");
			}
            
            response.sendRedirect("admin-pages/admin-courses.html");

            response.getWriter().write("File " + fileName + " uploaded for material: " + materialName + " in module: " );
        } catch (Exception e) {
        	response.sendRedirect("admin-pages/admin-dashboard.html");
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
