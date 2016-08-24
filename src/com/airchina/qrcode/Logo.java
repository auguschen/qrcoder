package com.airchina.qrcode;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Logo {
    /**
     * 给二维码图片添加Logo
     * 
     * @param qrPic
     * @param logoPic
     */
    public void addLogo_QRCode(File qrPic, File logoPic, LogoConfig logoConfig)
    {
        try
        {
            if (!qrPic.isFile() || !logoPic.isFile())
            {
                System.out.print("file not find !");
                System.exit(0);
            }
 
            /**
             * 读取二维码图片，并构建绘图对象
             */
            BufferedImage image = ImageIO.read(qrPic);
            Graphics2D g = image.createGraphics();
 
            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(logoPic);
             
            int widthLogo = logo.getWidth(), heightLogo = logo.getHeight();
             
            // 计算图片放置位置
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - logo.getHeight()) / 2;
 
            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
            g.setStroke(new BasicStroke(logoConfig.getBorder()));
            g.setColor(logoConfig.getBorderColor());
            g.drawRect(x, y, widthLogo, heightLogo);
             
            g.dispose();
             
            ImageIO.write(image, "jpeg", new File("D:/newPic.jpg"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
 

}
