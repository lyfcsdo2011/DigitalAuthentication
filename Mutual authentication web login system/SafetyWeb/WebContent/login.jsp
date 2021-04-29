<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="jquery-1.11.0.min.js"></script>
	<!--导入jquery-->
	<script src="jquery-3.3.1.js"></script>
</head>
<body>
<form id="loginForm" action="userlogin.do?" method="post" accept-charset="utf-8">
        			<input type="hidden" name="action" value="login"/>
					<input id="username" name="username" type="text" placeholder="请输入账号"  onchange="checkUser()" autocomplete="off">
        			<input name="password" type="password" placeholder="请输入密码" autocomplete="off">
        			  <input name="randomCode" type="text" placeholder="请输入验证码" autocomplete="off">
                      <a href="javaScript:randomcode_refresh()"><img id="randomcode_img"  onclick="refreshRandom()" 
                      src="randomCode.captcha"  height="30" width="120"></a>
             

                     	<tr>
							<td class="td_left"></td>
							<td class="td_right check"><input type="submit"
								class="submit" value="登录"> </td>
						</tr>
        		</form>
        		
<script>
		function randomcode_refresh(){
			$("#randomcode_img").attr("src",
					"randomCode.captcha?date=" + new Date());
		}
    </script>
</body>
</html>