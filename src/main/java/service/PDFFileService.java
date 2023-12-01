package service;

import java.util.ArrayList;

import dao.PDFFileDAO;
import dao.UserDAO;
import model.PDFFile;
import model.User;

public class PDFFileService {
	PDFFileDAO pdfFileDAO = new PDFFileDAO();
	public int insert(PDFFile pdfFile) {
		return pdfFileDAO.insert(pdfFile);
	}

}
