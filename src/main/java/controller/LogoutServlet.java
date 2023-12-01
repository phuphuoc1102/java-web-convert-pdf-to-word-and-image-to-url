package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processLogout(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processLogout(request, response);
	}

	private void processLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy đối tượng session từ request
		HttpSession session = request.getSession(false);

		// Kiểm tra xem session có tồn tại không
		if (session != null) {
			// Xóa thông tin người dùng khỏi session
			session.removeAttribute("username");

			// Hủy session
			session.invalidate();
		}

		// Chuyển hướng người dùng đến trang login hoặc trang chính tùy thuộc vào yêu
		// cầu của bạn
		response.sendRedirect("/CuoiKiLTM/");
	}
}