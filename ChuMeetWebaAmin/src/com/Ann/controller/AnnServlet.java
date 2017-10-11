package com.Ann.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admPril.model.AdmPrilService;
import com.admin.controller.AdminMailService;
import com.admin.model.AdminService;
import com.admin.model.AdminVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

public class AnnServlet  extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);	
}

public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	req.setCharacterEncoding("UTF-8");
	String action = req.getParameter("action");
	
	
	   if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					String adminID = req.getParameter("adminID");
					String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (adminID == null || adminID.trim().length() == 0) {
						errorMsgs.put("adminID","�޲z���s��: �ФŪť�");
					} else if(!adminID.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.put("adminID","�޲z���s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
		            }
					
					String annName = req.getParameter("annName").trim();
					if (annName == null || annName.trim().length() == 0) {
						errorMsgs.put("annName","¾��ФŪť�");
					}
					
					java.sql.Date hiredate = null;
					try {
						hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
					} catch (IllegalArgumentException e) {
						errorMsgs.put("hiredate","�п�J���");
					}
					
					Double sal = null;
					try {
						sal = new Double(req.getParameter("sal").trim());
					} catch (NumberFormatException e) {
						errorMsgs.put("sal","�~���ж�Ʀr");
					}
					
					Double comm = null;
					try {
						comm = new Double(req.getParameter("comm").trim());
					} catch (NumberFormatException e) {
						errorMsgs.put("comm","�����ж�Ʀr");
					}
					
					Integer deptno = new Integer(req.getParameter("deptno").trim());

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					EmpService empSvc = new EmpService();
					empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					String url = "/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.put("Exception",e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
				}
			}

}
}