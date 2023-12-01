package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ImageFile;

public class ImageFileDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/ltm";
	String dbUser = "root";
	String dbPassword = "";

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public int insert(ImageFile imageFile) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			String sql = "INSERT INTO image (id, username, url) " + "VALUES (?, ?, ?)";
			// Sử dụng kết nối để thực hiện truy vấn SQL
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, imageFile.getId().toString());
			preparedStatement.setString(2, imageFile.getUsername());
			preparedStatement.setString(3, imageFile.getUrl());
			int x = preparedStatement.executeUpdate();
			return x;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
}
