<%@ page contentType="text/html" pageEncoding="utf-8" import="java.util.*, beans.Tasks"%>
<%-- 
	<jsp:useBean id="task" class="beans.Tasks" scope="session"> 
		<jsp:setProperty name="task" property="*" />
	</jsp:useBean>
--%>
<!DOCTYPE html>
<html>
	<style type="text/css">
	table{border-top:1px solid #999;border-left:1px solid #999;border-spacing:0;}

	table td{border-bottom:1px solid #999;border-right:1px solid #999;padding:10px 20px;}
	
	table th{border-bottom:1px solid #999;border-right:1px solid #999;padding:10px 20px;}
	</style>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
	</head>
	<body >
		<h1 align="center">Home</h1>
		<p align="right"><a href="user.delete.do">注销</a></p>
		<hr>
		<br>
		<br>
		<br>
		<table align="center">
			<tr>
				<th>任务名称</th>
				<th>任务类型</th>
				<th>完成状态</th>
				<th colspan="2" >操作</th>
			</tr>
			<%
				request.setCharacterEncoding("UTF-8");
				ArrayList<Tasks> tasklist = (ArrayList<Tasks>)session.getAttribute("tasklist");
				for(Tasks task : tasklist) {
					int Id = task.getId();
			%>
			<tr>
				<td><%= task.getTaskname() %></td>
				<td><%= task.getType() %></td>
				<td><%
						if(task.getAccomplished() == 0)
							out.print("未完成");
						else
							out.print("已完成");
					%>
				</td>
				<td>
					<a href="task.update.jsp?id=<%=Id%>&&taskname=<%=task.getTaskname()%>">修改</a>
				</td>
				<td>
					<a href="task.delete.do?id=<%=Id%>">删除</a>
				</td>
			</tr>
			
			<%
				}
			%>
		
		</table>
		
		<p align="center"><a href="task.add.jsp">添加新任务</a></p>
		
	</body>
</html>
