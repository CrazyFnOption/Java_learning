package Download;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("filename");

        // 修改中文文件名无法访问的问题
        String s = request.getHeader("user-agent");
        // 然后用一些工具类去编码相应的处理文件名
        //filename = util.getfilename(s,filename);


        // 获取真实路径，并且更改响应头,并且获取文件类型
        ServletContext servletContext = this.getServletContext();
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
        response.setHeader("content-disposition","attachment;filename=" + filename);
        String realName = null;
        if (mimeType.contains("video"))
            realName = servletContext.getRealPath("mp4/" + filename);
        else realName = servletContext.getRealPath("img/" + filename);

        FileInputStream fis = new FileInputStream(realName);
        BufferedInputStream bifs = new BufferedInputStream(fis);
        ServletOutputStream sos = response.getOutputStream();

        byte []buffer = new byte[1024];
        int len;
        while ((len = bifs.read(buffer)) != -1) {
            sos.write(buffer,0,len);
        }
        bifs.close();
        fis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
