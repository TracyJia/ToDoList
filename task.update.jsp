<%@ page contentType="text/html" pageEncoding="utf-8" %>

<html>
<head>
<meta charset="UTF-8">
<title>Update Task</title>
</head>
<body>
	<h1 align="center">Update Task</h1>
	<hr>
	<br>
	<br>
	<br>
	<%request.setCharacterEncoding("UTF-8"); %>
	
	<form action="task.update.do?id=<%=request.getParameter("id") %>" method="POST">
		<table align="center">
			<tr>
				<td align="right">任务名称&nbsp;&nbsp;</td>
				<%
					String str = request.getParameter("taskname");
					str = new String(str.getBytes("iso8859-1"),"utf-8");
						%>
				<td><input type="text" name="taskname" value="<%=str%>"></td>
			</tr>
			<tr>
				<td align="right">任务类型&nbsp;&nbsp;</td>
				<td><input type="radio" name="type" value="工作"></td>
				<td><input type="radio" name="type" value="家庭"></td>
			</tr>
			<tr>
				<td align="right">&nbsp;&nbsp;<input type="submit" value="确认"></td>
				<td>&nbsp;&nbsp;<input type="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>