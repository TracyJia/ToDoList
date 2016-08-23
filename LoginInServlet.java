// Java Resource/src/servlet/LoginInServlet.java
// ִ���û���¼����

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import beans.Users;

@WebServlet("/login.do")
public class LoginInServlet extends HttpServlet {

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
//		LoginInServlet ls = new LoginInServlet();
//		ls.init();
//	}
//	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	// �����û������ email �����ݿ��в�ѯ���û��Ƿ���ڣ�
	// �������ж����������Ƿ���ȷ��������ȷ��ת����¼�ɹ�ҳ�棬���������ת����¼ʧ��ҳ��
	// �û������ڣ�����ת����¼ʧ��ҳ��	
		String email = request.getParameter("email");
		System.out.println(email);
		try {
			String sql = "SELECT * FROM users WHERE email=?";  
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email); // �� sql ����е�һ���ʺ�����Ϊ���� email ��ֵ
			ResultSet rs = pstmt.executeQuery();
			//System.out.println(request.getSession().getId());
			
			
			if (rs.next()) { // �����ѯ�н��
				if (rs.getString("password").equals(request.getParameter("password"))) { // ������������ݿ��ж�Ӧ������ͬ
					Users user = new Users();
					user.setEmail(rs.getString("email"));
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					//System.out.println("SESSION:" + request.getSession().getAttributeNames());
					request.getSession().setAttribute("email", email); // ���Ự�������ԣ���Ϊ "user"��ֵΪ���� user
					//System.out.println(request.getSession().getAttribute("email"));
					//System.out.println("SESSION:" + request.getSession().getAttributeNames());
					//System.out.println(request.getSession().getValueNames());
					response.sendRedirect("login.successful.html");
				} 
				else { // ������������ݿ��ж�Ӧ���벻ͬ
					response.sendRedirect("login.error.html");
					System.out.println(rs.getString("password") + request.getParameter("password"));
					return;
				}
			} // end if (rs.getString("password") == request.getParameter("password"));
			else { // �����ѯ�޽��
				response.sendRedirect("login.error.html");
				return;
			} // end if (rs.next());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} // void doGet();
	
} // class LoginInServlet;
