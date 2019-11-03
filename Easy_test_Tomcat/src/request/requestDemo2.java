package request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/requestDemo2")
public class requestDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        System.out.println(method);

        String contextPath = request.getContextPath();
        System.out.println(contextPath);

        String servletPath = request.getServletPath();
        System.out.println(servletPath);

        String queryString = request.getQueryString();
        System.out.println(queryString);

        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);

        String protocol = request.getProtocol();
        System.out.println(protocol);

        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求头数据
        Enumeration<String> headNames = request.getHeaderNames();
        while (headNames.hasMoreElements()) {
            String name = headNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + "---" + value);
        }
    }
}
