<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
<link rel="icon"
	href="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/1200px-BMW.svg.png"
	type="image/x-icon">

<link href="styles/index.css" rel="stylesheet" />
<link href="styles/sharedStyles.css" rel="stylesheet" />

</head>
<body>
	<h1>Login Success</h1>
	<form action="search.do" id="search" method="post">

		<table>
			<tr>
				<td colspan="2"><c:if test="${requestScope.Err!=null}">
						<font color="red">${requestScope.Err}</font>
					</c:if></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${requestScope.numExc!=null}">
						<font color="red">${requestScope.numExc}</font>
					</c:if></td>
			</tr>
			<tr>
				<td colspan="2"><c:if
						test="${requestScope.databaseConnection!=null}">
						<font color="red">${requestScope.databaseConnection}</font>
					</c:if></td>
			</tr>
			<tr>
				<td>nv_ws_order_id</td>
				<td><input type="text" name="nv_ws_order_id" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Search"></td>
			</tr>
		</table>
	</form>
</body>
</html>