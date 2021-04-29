<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<form action="../logout.do" method="post">
<body>
登录成功！
<br>
${sessionScope.users.userName }，欢迎您！
<input name="submit" type="submit" value="退出">
</body>
</form>
</html>