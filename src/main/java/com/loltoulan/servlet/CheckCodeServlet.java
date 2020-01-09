package com.loltoulan.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author LOL_toulan
 * @Time 2020/1/9 20:59
 * @Message
 */
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 90;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics graphics = image.getGraphics();
        //设置画笔颜色为灰色
        graphics.setColor(Color.GRAY);
        //填充图片
        graphics.fillRect(0, 0, width, height);

        //产生6个随机验证码
        String checkCode = getCheckCode();
        //将验证码放入session中
        req.getSession().setAttribute("CHECKCODE_SERVER", checkCode);

        //画干扰线
        Random r = new Random();
        graphics.setColor(Color.GREEN);
        for (int i = 0; i < 6; i++) {
            int x1 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int x2 = r.nextInt(width);
            int y2 = r.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        //设置画笔颜色为黄色
        graphics.setColor(Color.YELLOW);
        //设置字体的小大
        graphics.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        graphics.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",resp.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGHIGKL0123456789MNOPQRSYUVWXYZ01234abcdefghigklmnopqrsyuvwxyz";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=5;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
