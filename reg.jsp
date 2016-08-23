<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoList Register</title>
</head>
<body>
	<h1 align="center">Register</h1>
	<hr>
	<br>
	<br>
	<br>
	<form action="reg.do" method="POST">
		<table align="center">
			<tr>
				<td align="right">name&nbsp;&nbsp;</td>
				<td><input type="text" name="username" ></td>
			</tr>
			<tr>
				<td align="right">email&nbsp;&nbsp;</td>
				<td><input type="text" name="email" ></td>
			</tr>
			<tr>
				<td align="right">password&nbsp;&nbsp;</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td align="right">confirm password&nbsp;&nbsp;</td>
				<td><input type="password" name="conpassword"></td>
			</tr>
			<tr>
				<td align="right">&nbsp;&nbsp;<input type="submit" value="submit"></td>
				<td>&nbsp;&nbsp;<input type="reset" value="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>