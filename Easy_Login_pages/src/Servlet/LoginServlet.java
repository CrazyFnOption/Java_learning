package Servlet;


import DAO.userDAO;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("check");

        HttpSession session = request.getSession();
        String checkCode = (String)session.getAttribute("checkCode");

        if (checkCode != null && checkCode.equalsIgnoreCase(checkcode)) {
            User loginUser = new User(username,password);
            userDAO dao = new userDAO();
            try {
                User user = dao.login(loginUser);
                if (user == null) {
                    request.setAttribute("sp_error","不好意思，用户名密码错误");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }
                else {
                    session.setAttribute("user",user.getUsername());
                    response.sendRedirect(request.getContextPath() + "/success.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else {
            request.setAttribute("cc_error", "验证码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
