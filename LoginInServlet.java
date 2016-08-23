// Java Resource/src/servlet/LoginInServlet.java
// 执行用户登录操作

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
//		LoginInServlet ls = new LoginInServlet();
//		ls.init();
//	}
//	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	// 根据用户输入的 email 在数据库中查询该用户是否存在，
	// 存在则判断密码输入是否正确，密码正确跳转至登录成功页面，密码错误跳转至登录失败页面，
	// 用户不存在，则跳转至登录失败页面	
		String email = request.getParameter("email");
		System.out.println(email);
		try {
			String sql = "SELECT * FROM users WHERE email=?";  
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email); // 将 sql 语句中第一个问号设置为变量 email 的值
			ResultSet rs = pstmt.executeQuery();
			//System.out.println(request.getSession().getId());
			
			
			if (rs.next()) { // 如果查询有结果
				if (rs.getString("password").equals(request.getParameter("password"))) { // 如果密码与数据库中对应密码相同
					Users user = new Users();
					user.setEmail(rs.getString("email"));
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					//System.out.println("SESSION:" + request.getSession().getAttributeNames());
					request.getSession().setAttribute("email", email); // 给会话设置属性，键为 "user"，值为对象 user
					//System.out.println(request.getSession().getAttribute("email"));
					//System.out.println("SESSION:" + request.getSession().getAttributeNames());
					//System.out.println(request.getSession().getValueNames());
					response.sendRedirect("login.successful.html");
				} 
				else { // 如果密码与数据库中对应密码不同
					response.sendRedirect("login.error.html");
					System.out.println(rs.getString("password") + request.getParameter("password"));
					return;
				}
			} // end if (rs.getString("password") == request.getParameter("password"));
			else { // 如果查询无结果
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
