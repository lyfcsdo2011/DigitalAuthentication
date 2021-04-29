package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.domain.Users;
import com.utils.SHAUtil;

@WebServlet("/userlogin.do")
public class UserLoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1 接收用户请求，获取请求数据
		HttpSession session = request.getSession();
		String strCode= (String) session.getAttribute("random-captcha");
				String userName = request.getParameter("username");
				// 密码获取后进行SHA256加密
				String userPassword = SHAUtil.getSHA256((request.getParameter("password")));
				String randomCode = request.getParameter("randomCode");
				Users user = UserDAO.getUser(userName);
				//2 调用业务逻辑
					if(strCode.equalsIgnoreCase(randomCode) && user.getUserName().equals(userName) && user.getUserPassword().equals(userPassword)) {
					//将用户保存到会话
						session.setAttribute("users", user);
						//3 页面导航，登录成功重定向
						response.sendRedirect(request.getContextPath()+"/users/loginSuccess.jsp");
					}else {
						request.getRequestDispatcher("/loginFailed.jsp").forward(request, response);
					}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
