package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/login" })
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        String urlTarget = "/login.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(urlTarget);
        rd.forward(request, response);
    }

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        String urlTarget = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        model.bo.Login bo = new model.bo.Login();
        if (bo.isVaildUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            urlTarget = "index";
        } else {
            urlTarget = "login";
        }

        response.sendRedirect(urlTarget);
    }
}
