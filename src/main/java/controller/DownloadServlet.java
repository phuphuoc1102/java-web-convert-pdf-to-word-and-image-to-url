package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aspose.pdf.DocSaveOptions;
import com.aspose.pdf.Document;

import dao.PDFFileDAO;
import model.PDFFile;
import service.PDFFileService;

/**
 * Servlet implementation class DowloadServlet
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String outputFileName = request.getSession().getAttribute("path").toString();
		Path filePath = Path.of(outputFileName);
		// Set response content type
		response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

		// Set the header for the file to be downloaded
		outputFileName= outputFileName.substring(outputFileName.lastIndexOf("\\")+1);
	    outputFileName = URLEncoder.encode(outputFileName, "UTF-8").replace("+", "%20");
		response.setHeader("Content-Disposition", "attachment; filename=" + outputFileName);
		UUID uuid = UUID.randomUUID();
		String username = (String) request.getSession().getAttribute("username");
		PDFFile pdfFile = new PDFFile(uuid, username, outputFileName);
		PDFFileService fileService = new PDFFileService();
		fileService.insert(pdfFile);
		// Copy the file content to the response output stream
		Files.copy(filePath, response.getOutputStream());
		Files.delete(filePath);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
