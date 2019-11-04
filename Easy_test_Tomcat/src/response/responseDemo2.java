package response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/responseDemo2")
public class responseDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo2...");
//        或者直接在下面进行更改下面的响应头代码，更改以下的方式
//        response.setCharacterEncoding("utf-8");
//        防止编码不一致 有两种方式
        response.setHeader("content-type","text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write("hello guys 我爱你们");

//        第二种方式就是 api给出的一个简单的方式
        response.setContentType("text/html;charset=utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
