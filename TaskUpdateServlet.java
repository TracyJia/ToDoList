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

@WebServlet("/task.update.do")
public class TaskUpdateServlet extends HttpServlet {

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
		String id = request.getParameter("id");
		String email = (String) request.getSession().getAttribute("email");
		String type = request.getParameter("type");
		System.out.println(email);
		
			try {
				String sql = "UPDATE tasks SET (taskname=?,type=?) WHERE id=? ";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, taskname); // �� sql ����е�һ���ʺ�����Ϊ���� email ��ֵ
				pstmt.setString(2, type);
				pstmt.setString(3, id);
				pstmt.executeUpdate();
				response.sendRedirect("update.successful.html");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	} // void doGet();

} // class LoginInServlet;
