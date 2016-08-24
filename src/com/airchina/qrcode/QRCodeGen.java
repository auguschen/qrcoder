package com.airchina.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swetake.util.Qrcode;

/**
 * Servlet implementation class QRCodeGen
 */
@WebServlet("/qrc")
public class QRCodeGen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QRCodeGen() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("image/jpg");
		String code = request.getParameter("code");
		Integer size = 5;

		try {
			size = Integer.parseInt(request.getParameter("size"));
		} catch (Exception e){
			
		}
		
		byte[] d = code.getBytes("ISO-8859-1");
		
		Qrcode testQrcode = new Qrcode();
		testQrcode.setQrcodeErrorCorrect('M');
		testQrcode.setQrcodeEncodeMode('B');
		testQrcode.setQrcodeVersion(7);
		BufferedImage image = new BufferedImage(45*size + size * 2 , 45*size + size * 2,
				BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g = image.createGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 45*size + size * 2, 45*size + size * 2);
		g.setColor(Color.BLACK);
		if (d.length > 0 && d.length < 240) {
			boolean[][] s = testQrcode.calQrcode(d);
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if (s[j][i]) {
						g.fillRect(j * size + size, i * size + size, size, size);
					}
				}
			}
		}
		g.dispose();
		image.flush();
		ImageIO.write(image, "jpg", response.getOutputStream());

	}

}
