package com.admin.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.admin.model.*;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//�s�W���u���
		if("insert".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("erroeMsgs", errorMsgs);
		
			try{			
				String adminName=req.getParameter("adminName");		
				System.out.println(req.getParameter("adminName"));
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(adminName == null || adminName.trim().length() == 0){
					errorMsgs.put("adminName", "�m�W:�ФŪť�");
				}else if(!adminName.trim().matches(adminNameReg)){
					errorMsgs.put("adminName", "�m�W:�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				String adminMail=req.getParameter("adminMail").trim();
				if (adminMail == null || adminMail.trim().length() == 0){
					errorMsgs.put("adminMail","�b���Ūť�");
				}
				
				
				String adminPW =req.getParameter("adminPW").trim();
					if(adminPW == null || adminPW.trim().length() == 0){
						errorMsgs.put("adminPW","�K�X�۰ʲ���");
					}
				
				
				
				
				
				String adminEmail=req.getParameter("adminEmail").trim();
					if(adminEmail == null || adminEmail.trim().length() == 0){
						errorMsgs.put("adminEmail","�H�c�Ūť�");						
					}
					
				java.sql.Timestamp adminDate = null;
				try{
					adminDate = java.sql.Timestamp.valueOf(req.getParameter("adminDate").trim());					
				}catch  (IllegalArgumentException e) {
					errorMsgs.put("adminDate","�п�J���");
			}
				Integer adminStatus = null;
				try{
					adminStatus = new Integer(req.getParameter("adminStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("adminStatus","���A");
				}
				
			   
			// Send the use back to the form, if there were errors
			   if(!errorMsgs.isEmpty()){
				   RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				    failureView.forward(req,res);
				    return;
			   }
			   //�}�l�s�W���
			   AdminService adminSvc = new AdminService();
			   adminSvc.addAdmin(adminName,adminMail,adminPW,adminEmail,adminDate,adminStatus);
			   
			   //�s�W�������
			   String url = "/back-end/admin.jsp";
			   RequestDispatcher successView = req.getRequestDispatcher(url);
			   successView.forward(req, res);
			   
			}catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
	
		if ("delete".equals(action)) { // �Ӧ�admin.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer adminID = new Integer(req.getParameter("adminID"));
				
				/***************************2.�}�l�R�����***************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.deleteAdmin(adminID);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
	}
		}	
	
	