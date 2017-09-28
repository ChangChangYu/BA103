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

		AdminVO adminNameVO = adminSvc.getAdminByAdminName(adminName);
//		                印出JSP有沒有送東西過來,或者有沒有取到值		
//		System.out.println(adminNameVO.getAdminName());
//		System.out.println(adminNameVO.getAdminPW());
		if (adminName.equals(adminNameVO.getAdminName()) && adminPW.equals(adminNameVO.getAdminPW()))			
			return true;
		 else
			return false;
		// TODO Auto-generated constructor stub
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
			RequestDispatcher failureView = req.getRequestDispatcher("backlogin.jsp");
			failureView.forward(req, res);
			return;
		}
		if (!BackLogin(adminName, adminPW)) {
			errorMsgs.add("帳號,密碼");
			RequestDispatcher failureView = req.getRequestDispatcher("backlogin.jsp");
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
			res.sendRedirect(req.getContextPath() + "/Back-end/admin.jsp");

		}
	}

}
