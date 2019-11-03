package request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestDemo3")
public class requestDemo3 extends HttpServlet {
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

    // 这里就不再演示 refer的地址检验作用了，就不再这个地方进行解释了  就是在后台根据代码进行校验
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求头数据
        String agent = request.getHeader("user-agent");
        if (agent.contains("Safari")) {
            System.out.println("Safari is coming");
        }
        if (agent.contains("Chrome")) {
            System.out.println("Chromes is coming");
        }
        else if (agent.contains("Firefox")) {
            System.out.println("FireFox is coming");
        }
    }
}
