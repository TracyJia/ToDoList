// Java Resource/src/servlet/TaskAddServlet.java
// 添加任务

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
		String type = request.getParameter("type");
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		
			try {
				String sql = "INSERT INTO tasks (taskname,user_email, type) VALUES (?,?,?) ";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, taskname); // 将 sql 语句中第一个问号设置为变量 email 的值
				pstmt.setString(2, email);
				pstmt.setString(3, "type");
				pstmt.executeUpdate();
				response.sendRedirect("add.successful.html");
			}catch(Exception ex){
				ex.printStackTrace();
			} // end try
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} // void doGet();

} // class LoginInServlet;
