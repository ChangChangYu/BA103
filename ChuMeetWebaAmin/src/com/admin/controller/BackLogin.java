package com.admin.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;

/**
 * Servlet implementation class BackLogin
 */

public class BackLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected boolean BackLogin(String adminName,String adminPW) {
		AdminService adminSvc = new AdminService();
		List<AdminVO> adminList = adminSvc.getAll();
		//帳號是否存在資料庫裡
		Boolean isAdminName = false;
		Boolean isAdminPw = false;
		//從資料庫裡撈出所有管理員帳號一筆一筆比對
		for (AdminVO adminVO : adminList) {
			String serverAdminName = adminVO.getAdminName().toString();
			if(adminName.equals(serverAdminName))
				isAdminName=true;
			//存在的話boolean為true
		}
			if(isAdminName){
				AdminVO adminVO = adminSvc.getAdminByAdminName(adminName);
				if(adminPW.equals(adminVO.getAdminPW()))
					isAdminPw=true;
			}
			System.out.println("您輸入帳號"+adminName);
			System.out.println("您輸入密碼"+adminPW);
			System.out.println("帳號過不過"+isAdminName);
			System.out.println("密碼過不過"+isAdminPw);
		if(isAdminName&&isAdminPw)
			return true;
		else
			return false;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMags", errorMsgs);

		String adminName = req.getParameter("adminName");
		String adminPW = req.getParameter("adminPW");
		
		if (adminName == null || (adminName.trim()).length() == 0) {
			errorMsgs.add("錯誤");
		}
		if (adminPW == null || (adminPW.trim()).length() == 0) {
			errorMsgs.add("錯誤");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("backLogin.jsp");
			failureView.forward(req, res);
			return;
		}
		if (!BackLogin(adminName, adminPW)) {
			errorMsgs.add("帳號,密碼");
			RequestDispatcher failureView = req.getRequestDispatcher("backLogin.jsp");
			failureView.forward(req, res);
			return;
		} else {
			AdminService adminService = new AdminService();
			AdminVO adminVO = adminService.getAdminByAdminName(adminName);
			HttpSession session = req.getSession();
			session.setAttribute("adminName", adminName);
			session.setAttribute("adminVO", adminVO);
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/back-end/admin.jsp");

		}
	}

}
