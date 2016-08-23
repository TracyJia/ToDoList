// Java Resource/src/servlet/UserDeleteServlet.java
// 注销账户

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
	public void init() { // 建立数据库连接
		
		String DRIVER = "com.mysql.jdbc.Driver"; // 数据库驱动
		// 连接数据库的 URL 地址
		String URL = "jdbc:mysql://localhost:3306/todolist?useUnicode=true&characterEncoding=UTF-8"; 
		String USERNAME = "root";
		String PASSWORD = "root";
		
		try {
			Class.forName(DRIVER); // 加载驱动程序
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 创建连接对象
			System.out.println("ok!");
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println("something wrong!");
		}
	} // void init();
	
	// public static void main(String[] args) { // 测试数据库
	// 	UserDeleteServlet ls = new UserDeleteServlet();
	// 	ls.init();
	// }
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String email = (String) request.getSession().getAttribute("email");
		try {
				String sql = "DELETE FROM users WHERE email='" + email + "'";  
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, taskname); // 将 sql 语句中第一个问号设置为变量 email 的值
				//pstmt.setString(1, id);
				pstmt.executeUpdate();
				response.sendRedirect("index.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end try
		
	} // void doPost();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	} // void doGet();

} // class UserDeleteServlet;
