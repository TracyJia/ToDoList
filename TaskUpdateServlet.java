// Java Resource/src/servlet/TaskUpdateServlet.java
// 修改任务

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
	public void init() { // 建立数据库连接
		
		String DRIVER = "com.mysql.jdbc.Driver"; // 数据库驱动
		// 连接数据库的 URL 地址
		String URL = "jdbc:mysql://localhost:3306/todolist?useUnicode=true&characterEncoding=UTF-8"; 
		String USERNAME = "root";
		String PASSWORD = "root";
		
		try {
			Class.forName(DRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 创建连接对象
			//System.out.println("ok!");
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println("something wrong!");
		}
	} // void init();
	
//	public static void main(String[] args) { // 测试数据库
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
				pstmt.setString(1, taskname); // 将 sql 语句中第一个问号设置为变量 email 的值
				pstmt.setString(2, type);
				pstmt.setString(3, id);
				pstmt.executeUpdate();
				response.sendRedirect("update.successful.html");
			}catch(Exception ex){
				ex.printStackTrace();
			} // end try
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} // void doGet();

} // class TaskUpdateServlet;
