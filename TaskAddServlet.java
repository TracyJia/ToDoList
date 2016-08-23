// Java Resource/src/servlet/LoginInServlet.java
// ִ���û���¼����

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/task.add.do")
public class TaskAddServlet extends HttpServlet {

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
//		TaskAddServlet ls = new TaskAddServlet();
//		ls.init();
//	}
//	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		String taskname = request.getParameter("taskname");
		String type = request.getParameter("type");
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		
			try {
				String sql = "INSERT INTO tasks (taskname,user_email, type) VALUES (?,?,?) ";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, taskname); // �� sql ����е�һ���ʺ�����Ϊ���� email ��ֵ
				pstmt.setString(2, email);
				pstmt.setString(3, "type");
				pstmt.executeUpdate();
				response.sendRedirect("add.successful.html");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	} // void doGet();

} // class LoginInServlet;
