// Java Resource/src/servlet/LoginInServlet.java
// ִ���û���¼����

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/user.delete.do")
public class UserDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	
	@Override
	public void init() { // �������ݿ�����
		
		String DRIVER = "com.mysql.jdbc.Driver"; // ���ݿ�����
		// �������ݿ�� URL ��ַ
		String URL = "jdbc:mysql://localhost:3306/todolist?useUnicode=true&characterEncoding=UTF-8"; 
		String USERNAME = "root";
		String PASSWORD = "root";
		
		try {
			Class.forName(DRIVER); // ������������
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // �������Ӷ���
			System.out.println("ok!");
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println("something wrong!");
		}
	} // void init();
	
	public static void main(String[] args) {
		UserDeleteServlet ls = new UserDeleteServlet();
		ls.init();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String email = (String) request.getSession().getAttribute("email");
		try {
				String sql = "DELETE FROM users WHERE email='" + email + "'";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, taskname); // �� sql ����е�һ���ʺ�����Ϊ���� email ��ֵ
				//pstmt.setString(1, id);
				pstmt.executeUpdate();
				response.sendRedirect("index.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} // void doGet();

} // class LoginInServlet;
