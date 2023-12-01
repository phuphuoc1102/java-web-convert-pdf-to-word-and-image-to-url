package service;

import java.util.ArrayList;

import dao.UserDAO;
import model.User;

public class UserService {
	UserDAO userDao = new UserDAO();
	public boolean isValidUser(String username, String password) {
		return userDao.isExistUser(username, password);
	}
	public int register(User user) {
		return userDao.AddUser(user);
	}

}
