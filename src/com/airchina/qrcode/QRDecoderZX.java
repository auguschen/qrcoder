package com.airchina.qrcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * Servlet implementation class QRDecoderZX
 */
@WebServlet("/zxd")
@MultipartConfig
public class QRDecoderZX extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QRDecoderZX() {
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
		response.setContentType("text/plain");
		String QRImageUrl = request.getParameter("url");
		BufferedImage bufferedImage = null;

		try {
			if (null!=QRImageUrl && !("".equals(QRImageUrl)) ) {
				bufferedImage = ImageIO.read(new URL(QRImageUrl));
			} else {
			    Part filePart = request.getPart("qrimagefile"); // Retrieves <input type="file" name="file">
			    InputStream filecontent = filePart.getInputStream();
				bufferedImage = ImageIO.read(filecontent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		Result result = null;

		try {
			result = new MultiFormatReader().decode(bitmap, hints);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		if (null!=QRImageUrl && !("".equals(QRImageUrl)) ) {
			response.getWriter().print(new String(result.toString().getBytes("ISO-8859-1"),"utf-8"));
		} else {
			response.getWriter().print(result.getText());	
		}
		
	}

}
