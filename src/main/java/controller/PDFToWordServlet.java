package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.aspose.pdf.Document;
import com.aspose.pdf.DocSaveOptions;

/**
 * Servlet implementation class PDFToWord
 */
@WebServlet("/pdf-to-word")
@MultipartConfig
public class PDFToWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PDFToWordServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");

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
			// Lấy đường dẫn tệp PDF từ yêu cầu
			Part filePart = request.getPart("pdfFile");
			InputStream pdfInputStream = filePart.getInputStream();
			String fileName = filePart.getSubmittedFileName();
			// Tạo tệp DOCX từ tệp PDF
			String path = convertPdfToWord(pdfInputStream, fileName);
			request.getSession().setAttribute("path", path);

			// Lưu đường dẫn tệp DOCX vào session để sử dụng sau này
			pdfInputStream.close();
			response.sendRedirect("/CuoiKiLTM/download");
			System.out.println("hhe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String convertPdfToWord(InputStream pdfInputStream, String fileName) throws IOException {
		// Đường dẫn thư mục bạn muốn lưu trữ tệp DOCX
        String targetDirectory = System.getProperty("user.home")+"\\Dowloads";
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		// Tạo một tệp tạm thời để lưu PDF
		Path tempPdfFile = Files.createTempFile("temp", ".pdf");
		Files.copy(pdfInputStream, tempPdfFile, StandardCopyOption.REPLACE_EXISTING);

		// Mở tệp PDF nguồn
		Document pdfDocument = new Document(tempPdfFile.toString());

		// Convert PDF sang DOCX
		DocSaveOptions saveOptions = new DocSaveOptions();
		saveOptions.setFormat(DocSaveOptions.DocFormat.DocX);

		// Tạo một tệp tạm thời để lưu DOCX trong thư mục cụ thể
		Path tempDocxFile = Paths.get(targetDirectory, fileName + ".docx");

		// Lưu tài liệu trong định dạng DOCX
		pdfDocument.save(tempDocxFile.toString(), saveOptions);
		System.out.println("CONVERT SUCCESSFULLY");

		// Trả về đối tượng InputStream của tệp DOCX kết quả
		return targetDirectory + "\\" + fileName + ".docx";
	}

}
