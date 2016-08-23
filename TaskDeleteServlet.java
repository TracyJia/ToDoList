// Java Resource/src/servlet/TaskDeleteServlet.java
// 删除任务

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

@WebServlet("/task.delete.do")
public class TaskDeleteServlet extends HttpServlet {

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
	
	// public static void main(String[] args) { // 测试数据库
	// 	TaskDeleteServlet ls = new TaskDeleteServlet();
	// 	ls.init();
	// }
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("id"));
		String id = request.getParameter("id");
		System.out.println(id);
		try {
				String sql = "DELETE FROM tasks WHERE id='" + id + "'";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				response.sendRedirect("QueryServlet.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end try
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} // void doGet();

} // class TaskDeleteServlet;
