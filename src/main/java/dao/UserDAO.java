package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import model.User;




public class UserDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/ltm";
	String dbUser = "root";
	String dbPassword = "";

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public boolean isExistUser(String username, String password) {
		try {
			// Tạo kết nối đến cơ sở dữ liệu
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

			// Sử dụng kết nối để thực hiện truy vấn SQL
			String sqlQuery = "SELECT password FROM user WHERE username = ?";
			preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, username);
			System.out.println(sqlQuery + username);
			resultSet = preparedStatement.executeQuery();

			// Xử lý kết quả truy vấn
			if (resultSet.next()) {
				String pass = resultSet.getString("password");

				if (password.equals(pass)) {
					System.out.println("true");
					return true;
				} else {
					// Sai mật khẩu
					System.out.println("password not correct");

					return false;
				}
			} else {
				// Không tìm thấy người dùng
				System.out.println("user not exists");

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Đóng tất cả các tài nguyên
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public int AddUser(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			String sql = "INSERT INTO user (username, password, firstName, lastName) " + "VALUES (?, ?, ?, ?)";
			// Sử dụng kết nối để thực hiện truy vấn SQL
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			int x = preparedStatement.executeUpdate();
			return x;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

}
