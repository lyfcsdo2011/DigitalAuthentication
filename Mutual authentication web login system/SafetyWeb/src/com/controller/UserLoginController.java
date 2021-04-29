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
		
		//1 �����û����󣬻�ȡ��������
		HttpSession session = request.getSession();
		String strCode= (String) session.getAttribute("random-captcha");
				String userName = request.getParameter("username");
				// �����ȡ�����SHA256����
				String userPassword = SHAUtil.getSHA256((request.getParameter("password")));
				String randomCode = request.getParameter("randomCode");
				Users user = UserDAO.getUser(userName);
				//2 ����ҵ���߼�
					if(strCode.equalsIgnoreCase(randomCode) && user.getUserName().equals(userName) && user.getUserPassword().equals(userPassword)) {
					//���û����浽�Ự
						session.setAttribute("users", user);
						//3 ҳ�浼������¼�ɹ��ض���
						response.sendRedirect(request.getContextPath()+"/users/loginSuccess.jsp");
					}else {
						request.getRequestDispatcher("/loginFailed.jsp").forward(request, response);
					}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
