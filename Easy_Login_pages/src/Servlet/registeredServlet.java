package Servlet;

import DAO.userDAO;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registeredServlet")
public class registeredServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("check");

        HttpSession session = request.getSession();
        String checkCode = (String)session.getAttribute("checkCode");
        if (checkCode != null && checkCode.equalsIgnoreCase(checkcode)) {
            User user = new User(username,password);
            userDAO dao = new userDAO();
            try {
                dao.registered(user);
                session.setAttribute("registered","恭喜你注册成功！！！");
                session.setAttribute("user",user.getUsername());
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            request.setAttribute("cc_error","不好意思，请重新写验证码");
            request.getRequestDispatcher("/registeredServlet").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
