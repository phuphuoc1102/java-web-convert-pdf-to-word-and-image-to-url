package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PDFFile;

public class PDFFileDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/ltm";
	String dbUser = "root";
	String dbPassword = "";

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public int insert(PDFFile pdfFile) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			String sql = "INSERT INTO pdf (id, username, path) " + "VALUES (?, ?, ?)";
			// Sử dụng kết nối để thực hiện truy vấn SQL
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pdfFile.getId().toString());
			preparedStatement.setString(2, pdfFile.getUsername());
			preparedStatement.setString(3, pdfFile.getPath());
			int x = preparedStatement.executeUpdate();
			return x;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
}
