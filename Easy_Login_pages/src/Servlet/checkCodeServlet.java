package Servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkCode = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();

        int width = 120;
        int height = 30;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width - 1, height - 1);

        StringBuilder stringBuilder = new StringBuilder();
        g.setColor(Color.BLUE);
        for (int i = 1; i <= 4; i++) {
            char ch = checkCode.charAt(random.nextInt(checkCode.length()));
            g.drawString(ch + "",  width / 5 * i, height / 2);
            stringBuilder.append(ch);
        }
        g.setColor(Color.GREEN);
        for (int i = 0; i < 5; i++) {
            g.drawLine(random.nextInt(width),random.nextInt(height),random.nextInt(width),random.nextInt(height));
        }


        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
        request.getSession().setAttribute("checkCode", stringBuilder.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
