<%@ page contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoList Log In</title>
</head>
<body >
	<h1 align="center">Log In</h1>
	<hr>
	<br>
	<br>
	<br>
	<form action="login.do" method="POST">
		<table align="center">
			<tr>
				<td align="right">email&nbsp;</td>
				<td><input type="text" name="email" value="" ></td>
			</tr>
			<tr>
				<td align="right">password&nbsp;</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td align="right">&nbsp;&nbsp;<input type="submit" value="log in"></td>
				<td>&nbsp;&nbsp;<input type="reset" value="reset"></td>
			</tr>
		</table>
	</form>


</body>
</html>