package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/", "/index" })
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        try {
            Object obj = request.getSession().getAttribute("user");
            if (obj == null)
                throw new Exception("User is not exist!");

            String user = obj.toString();
            if (user != null && !user.isEmpty()) {
                String urlTarget = "dashboard";
                response.sendRedirect(urlTarget);
                // String urlTarget = "/main.jsp";
                // RequestDispatcher rd = getServletContext().getRequestDispatcher(urlTarget);
                // rd.forward(request, response);    
            }
            else throw new Exception("User is not exist!");
        }
        catch (Exception ex) {
            String urlTarget = "login";
            response.sendRedirect(urlTarget);
        }
    }
}
