package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SessionCheckServlet")
public class SessionCheckServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the current session, if it exists
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("isLoggedIn") != null && (boolean) session.getAttribute("isLoggedIn")) {
            // User is logged in
            response.getWriter().write("User is logged in");
        } else {
            // User is not logged in
            response.getWriter().write("User is not logged in");
        }
    }
}
