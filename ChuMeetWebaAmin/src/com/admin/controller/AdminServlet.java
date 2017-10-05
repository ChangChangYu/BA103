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
		
		
		//新增員工資料
		if("insert".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("erroeMsgs", errorMsgs);
		
			try{			
				String adminName=req.getParameter("adminName");		
				System.out.println(req.getParameter("adminName"));
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(adminName == null || adminName.trim().length() == 0){
					errorMsgs.put("adminName", "姓名:請勿空白");
				}else if(!adminName.trim().matches(adminNameReg)){
					errorMsgs.put("adminName", "姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String adminMail=req.getParameter("adminMail").trim();
				if (adminMail == null || adminMail.trim().length() == 0){
					errorMsgs.put("adminMail","帳號勿空白");
				}
				
				
				String adminPW =req.getParameter("adminPW").trim();
					if(adminPW == null || adminPW.trim().length() == 0){
						errorMsgs.put("adminPW","密碼自動產生");
					}
				
				
				
				
				
				String adminEmail=req.getParameter("adminEmail").trim();
					if(adminEmail == null || adminEmail.trim().length() == 0){
						errorMsgs.put("adminEmail","信箱勿空白");						
					}
					
				java.sql.Timestamp adminDate = null;
				try{
					adminDate = java.sql.Timestamp.valueOf(req.getParameter("adminDate").trim());					
				}catch  (IllegalArgumentException e) {
					errorMsgs.put("adminDate","請輸入日期");
			}
				Integer adminStatus = null;
				try{
					adminStatus = new Integer(req.getParameter("adminStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("adminStatus","狀態");
				}
				
			   
			// Send the use back to the form, if there were errors
			   if(!errorMsgs.isEmpty()){
				   RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				    failureView.forward(req,res);
				    return;
			   }
			   //開始新增資料
			   AdminService adminSvc = new AdminService();
			   adminSvc.addAdmin(adminName,adminMail,adminPW,adminEmail,adminDate,adminStatus);
			   
			   //新增完成轉交
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
	
		if ("delete".equals(action)) { // 來自admin.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer adminID = new Integer(req.getParameter("adminID"));
				
				/***************************2.開始刪除資料***************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.deleteAdmin(adminID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
	}
		}	
	
	