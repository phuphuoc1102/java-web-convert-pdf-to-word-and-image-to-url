package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.aspose.pdf.Document;

import dao.PDFFileDAO;
import model.ImageFile;
import model.PDFFile;
import service.ImageFileService;

import com.aspose.pdf.DocSaveOptions;

/**
 * Servlet implementation class PDFToWord
 */
@WebServlet("/image-to-url")
@MultipartConfig
public class ImageToURLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageToURLServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/imageconverter.jsp");

		// Chuyển hướng đến trang /login.jsp
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Part filePart = request.getPart("imageFile");

			// Chuyển đổi ảnh thành URL base64
			String imageUrl = convertImageToUrl(filePart);
			System.out.println(imageUrl);
			// Gửi URL về trình duyệt hoặc sử dụng nó theo nhu cầu của bạn
			request.getSession().setAttribute("imageUrl", imageUrl);
			UUID uuid = UUID.randomUUID();
	        String username = (String)request.getSession().getAttribute("username");
			ImageFile imageFile = new ImageFile(uuid, username, imageUrl);
			ImageFileService imageFileService = new ImageFileService();
			imageFileService.insert(imageFile);
			response.sendRedirect(request.getRequestURI());
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Error converting image to URL.");
		}
	}

	private String convertImageToUrl(Part filePart) throws IOException {
		InputStream fileContent = filePart.getInputStream();
		byte[] imageData = new byte[fileContent.available()];
		fileContent.read(imageData);
		String base64Encoded = Base64.getEncoder().encodeToString(imageData);
		String imageUrl = "data:image/jpeg;base64," + base64Encoded;
		fileContent.close();
		return imageUrl;
	}

}
