// Java Resource/src/servlet/LoginInServlet.java
// ִ���û���¼����

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import beans.Tasks;

@WebServlet("/QueryServlet.do")
public class QueryServlet extends HttpServlet {

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
	
//	public static void main(String[] args) {
//		LoginInServlet ls = new LoginInServlet();
//		ls.init();
//	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	

			String email = (String)request.getSession().getAttribute("email");
			
			System.out.println(email);
				
			try {
				String sql = "SELECT * FROM tasks WHERE user_email=?";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email); // �� sql ����е�һ���ʺ�����Ϊ���� email ��ֵ
				ResultSet rs = pstmt.executeQuery();
				ArrayList<Tasks> tasklist = new ArrayList<Tasks>();
				
				//System.out.println(pstmt);
				//System.out.println(rs);
				//System.out.println(request.getSession().getId());
				//System.out.println(request.getSession().getValueNames());
				if(rs.next()){
					rs = null;
					rs = pstmt.executeQuery();
					while (rs.next()) { // �����ѯ�н��
						Tasks task = new Tasks();
						task.setEmail(rs.getString("user_email"));
						task.setId(rs.getInt("id"));
						task.setTaskname(rs.getString("taskname"));
						task.setAccomplished(rs.getInt("accomplished"));
						task.setType(rs.getString("type"));
						tasklist.add(task);
					} // end if (rs.next());
					request.getSession().setAttribute("tasklist", tasklist);
					response.sendRedirect("home.jsp");
				}
				else { // �����ѯ�޽��
					response.sendRedirect("home_null.jsp");
						//System.out.println(rs.getString("password") + request.getParameter("password"));
					return;
				} // end else
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end try
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} // void doGet();
	
} // class LoginInServlet;
