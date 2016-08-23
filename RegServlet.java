// Java Resource/src/servlet/LoginInServlet.java
// ִ���û���¼����

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {

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
			//System.out.println("ok!");
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println("something wrong!");
		}
	} // void init();
	
//	public static void main(String[] args) {
//		RegServlet ls = new RegServlet();
//		ls.init();
//	}
//	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String email = request.getParameter("email");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String conpassword = request.getParameter("conpassword");
		
		request.getSession().setAttribute("email", request.getParameter("email"));
		if(!password.equals(conpassword))
			response.sendRedirect("reg.error.html");
		else{
			try {
				String sql = "INSERT INTO users (username,email, password) VALUES (?,?,?) ";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name); // �� sql ����е�һ���ʺ�����Ϊ���� email ��ֵ
				pstmt.setString(2, email);
				pstmt.setString(3, password);
				pstmt.executeUpdate();
				response.sendRedirect("reg.successful.html");
				//return;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	} // void doGet();

} // class LoginInServlet;
