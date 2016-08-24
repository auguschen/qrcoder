package com.airchina.qrcode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * Servlet implementation class QREncoder
 */
@WebServlet("/zxg")
public class QREncoderZX extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public QREncoderZX() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("image/png");
		String code = request.getParameter("code");
		byte[] d = code.getBytes("ISO-8859-1");
		
		Integer width = 200;

		try {
			width = Integer.parseInt(request.getParameter("w"));
		} catch (Exception e){
			
		}
		
		Integer height = 200;

		try {
			height = Integer.parseInt(request.getParameter("h"));
		} catch (Exception e){
			
		}
		
		@SuppressWarnings("rawtypes")
		Map<EncodeHintType, Comparable> hints = new HashMap<EncodeHintType, Comparable>();

		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 1);

	    QRCodeWriter qrWriter = new QRCodeWriter();
	    if (d.length > 0) {
			try {
			    BitMatrix bitMatrix = qrWriter.encode(new String(d,"utf-8"), BarcodeFormat.QR_CODE, width, height, hints);
			    
			    BufferedImage imagewithoutlogo = MatrixToImageWriter.toBufferedImage(bitMatrix);
			    
	            Graphics2D g_imagewithoutlogo = imagewithoutlogo.createGraphics();
	            
	            /**
	             * 读取Logo图片
	             */

	            BufferedImage logo = ImageIO.read(new URL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/logo32.png"));
	            
//	            LogoConfig logoConfig = new LogoConfig();
	            
	            int widthLogo = logo.getWidth();
	            int heightLogo = logo.getHeight();
	             
	            // 计算图片放置位置
	            int x = (imagewithoutlogo.getWidth() - widthLogo) / 2;
	            int y = (imagewithoutlogo.getHeight() - logo.getHeight()) / 2;
	 
	            //开始绘制图片
	            g_imagewithoutlogo.drawImage(logo, x, y, widthLogo, heightLogo, null);
//	            g_imagewithoutlogo.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
//	            g.setStroke(new BasicStroke(logoConfig.getBorder()));
//	            g.setColor(logoConfig.getBorderColor());
//	            g_imagewithoutlogo.drawRect(x, y, widthLogo, heightLogo);
	             
	            g_imagewithoutlogo.dispose();
	             
//	            ImageIO.write(imagewithoutlogo, "png", );

//	            BufferedImageLuminanceSource bils = new BufferedImageLuminanceSource(imagewithoutlogo);
//	            HybridBinarizer hb = new HybridBinarizer(bils);
//	            BitMatrix bm = hb.getBlackMatrix();
			    
//			    MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
//			    MatrixToImageWriter.writeToStream(bm, "png", response.getOutputStream());
	            ImageIO.write(imagewithoutlogo, "jpeg", response.getOutputStream());
			} catch (WriterException e) {
				e.printStackTrace();
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
			response.flushBuffer();
	    }
	}

}
