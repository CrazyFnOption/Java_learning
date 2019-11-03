package Servlet;

import DAO.userDAO;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginuser = new User(username,password);
        userDAO dao = new userDAO();
        User user = null;
        try {
            user = dao.login(loginuser);
            //System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            request.setAttribute("user",user);

            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
        else {
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }
    }
}
