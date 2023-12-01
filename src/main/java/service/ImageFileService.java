package service;

import java.util.ArrayList;

import dao.ImageFileDAO;
import dao.UserDAO;
import model.ImageFile;
import model.User;

public class ImageFileService {
	ImageFileDAO imageFileDAO = new ImageFileDAO();
	public int insert(ImageFile imageFile) {
		return imageFileDAO.insert(imageFile);
	}

}
