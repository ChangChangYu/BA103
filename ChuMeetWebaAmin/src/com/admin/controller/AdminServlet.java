package com.admin.controller;

import java.io.*;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.admin.model.*;

import com.admPril.model.*;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// �s�W���u���
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("erroeMsgs", errorMsgs);

			try {
				String adminName = req.getParameter("adminName");

				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.put("adminName", "�m�W:�ФŪť�");
				} else if (!adminName.trim().matches(adminNameReg)) {
					errorMsgs.put("adminName", "�m�W:�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				String adminMail = req.getParameter("adminMail").trim();
				if (adminMail == null || adminMail.trim().length() == 0) {
					errorMsgs.put("adminMail", "�b���Ūť�");
				}

				String adminEmail = req.getParameter("adminEmail").trim();
				if (adminEmail == null || adminEmail.trim().length() == 0) {
					errorMsgs.put("adminEmail", "�H�c�Ūť�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admPril.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println(adminName);
				System.out.println(adminMail);
				System.out.println(adminEmail);

				// �H�H�ϱK�X
				Random rand = new Random();
				String pw = encrypt(rand.toString());
				System.out.println(pw);
				AdminMailService mailService = new AdminMailService();
				// UUID
//				mailService.sendMail("ChuMeetService@gmail.com", "���}�޲z��ChuMeet �K�X�T�{�H",
//						"<img src=\"https://i.imgur.com/IN3wmJe.png\"><h2>�˷R�� " + adminName
//								+ " �z�n:</h2><br><p>�w��z�������}ChuMeet�޲z���C�K�X��</p><br><strong>" + pw
//								+ "</strong><br><p>ChuMeet�N�|���ѱz��h���A�ȸ�T�P���e�C </p><br><h5>ChuMeet�w��z���[�J! ChuMeet�A�ȹζ�</h5><br><h4>�p��������D�w��ӫHChuMeet�ȪA�H�c: chuMeetService@gmail.com</h4>");
				// �}�l�s�W���
				AdminService adminSvc = new AdminService();
				adminSvc.addAdmin(adminName, adminMail, pw, adminEmail, nowTimestamp(), 1);

				AdminVO adminVO = adminSvc.getAdminByAdminName(adminName);
				System.out.println(adminVO.getAdminName());
				// �v��
				AdmPrilService admPrilSvc = new AdmPrilService();
				String adminPrivCheckbox = req.getParameter("adminPrivCheckbox");
				if ("on".equals(adminPrivCheckbox))
					admPrilSvc.addAdmPril(1, adminVO.getAdminID(), nowTimestamp(), 1);
				String infoPrivCheckbox = req.getParameter("infoPrivCheckbox");
				if ("on".equals(infoPrivCheckbox))
					admPrilSvc.addAdmPril(2, adminVO.getAdminID(), nowTimestamp(), 1);
				String appPrivCheckbox = req.getParameter("appPrivCheckbox");
				if ("on".equals(appPrivCheckbox))
					admPrilSvc.addAdmPril(3, adminVO.getAdminID(), nowTimestamp(), 1);
				String memberPrivCheckbox = req.getParameter("memberPrivCheckbox");
				if ("on".equals(memberPrivCheckbox))
					admPrilSvc.addAdmPril(4, adminVO.getAdminID(), nowTimestamp(), 1);
				String achPrivCheckbox = req.getParameter("achPrivCheckbox");
				if ("on".equals(achPrivCheckbox))
					admPrilSvc.addAdmPril(5, adminVO.getAdminID(), nowTimestamp(), 1);
				String reportPrivCheckbox = req.getParameter("reportPrivCheckbox");
				if ("on".equals(reportPrivCheckbox))
					admPrilSvc.addAdmPril(6, adminVO.getAdminID(), nowTimestamp(), 1);
				String poiPrivCheckbox = req.getParameter("poiPrivCheckbox");
				if ("on".equals(poiPrivCheckbox))
					admPrilSvc.addAdmPril(7, adminVO.getAdminID(), nowTimestamp(), 1);
				String actPrivCheckbox = req.getParameter("actPrivCheckbox");
				if ("on".equals(actPrivCheckbox))
					admPrilSvc.addAdmPril(8, adminVO.getAdminID(), nowTimestamp(), 1);
				String clubPrivCheckbox = req.getParameter("clubPrivCheckbox");
				if ("on".equals(clubPrivCheckbox))
					admPrilSvc.addAdmPril(9, adminVO.getAdminID(), nowTimestamp(), 1);

				// �s�W�������
				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admPril.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer adminID = new Integer(req.getParameter("adminID"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AdminService adminSvc = new AdminService();
				AdminVO adminVO = adminSvc.getOneAdmin(adminID);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("adminVO", adminVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/updateAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				Integer adminID = null;
				String adminName = null;
				String adminMail = null;
				String adminPW = null;
				String adminEmail = null;

					adminID = new Integer(req.getParameter("adminID").trim());
					adminName = req.getParameter("adminName").trim();
					adminMail = req.getParameter("adminMail").trim();
					adminPW = req.getParameter("adminPW").trim();
					adminEmail = req.getParameter("adminEmail").trim();


				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.put("adminName", "�m�W:�ФŪť�");
				}
				if (!adminName.trim().matches(adminNameReg)) {
					errorMsgs.put("adminName", "�m�W:�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				if (adminMail == null || adminMail.trim().length() == 0) {
					errorMsgs.put("adminMail", "�b���Ūť�");
				}
				if (adminEmail == null || adminEmail.trim().length() == 0) {
					errorMsgs.put("adminEmail", "�H�c�Ūť�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/updateAdmin.jsp");
					failureView.forward(req, res);
					return;
				}

				AdminVO adminVO = new AdminVO();
				adminVO.setAdminID(adminID);
				adminVO.setAdminName(adminName);
				adminVO.setAdminMail(adminMail);
				adminVO.setAdminPW(adminPW);
				adminVO.setAdminEmail(adminEmail);
				adminVO.setAdminDate(nowTimestamp());
				adminVO.setAdminStatus(1);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminVO", adminVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/updateAdmin.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.updateAdmin(adminVO);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.put("�ק��ƥ���:" , e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/updateAdmin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("adminVO");
			session.removeAttribute("admPrilList");
			res.sendRedirect(req.getContextPath() + "/back-end/backLogin.jsp");
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�admin.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer adminID = new Integer(req.getParameter("adminID"));

				/*************************** 2.�}�l�R����� ***************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.deleteAdmin(adminID);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("ststus1".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer adminID = new Integer(req.getParameter("adminID"));
				// �}�l�d��

				AdminService adminSvc = new AdminService();
				adminSvc.status1(adminID);

				// �d�ߧ���,���

				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
	}

	// ------------------timestsmp--------------------

	// ���o�{�b��Timestamp�ɶ�
	public static Timestamp nowTimestamp() {
		java.util.Date utildate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
		java.sql.Time sTime = new java.sql.Time(utildate.getTime());
		java.sql.Timestamp stp = new java.sql.Timestamp(utildate.getTime());
		return stp;
	}
	
	//�[�K
	public static String encrypt(String s){   
		  MessageDigest sha = null;
		  
		  try{
		   sha = MessageDigest.getInstance("SHA-256");   
		   sha.update(s.getBytes());   
		  }catch(Exception e){
		   e.printStackTrace();
		   return "";
		  }

		  return byte2hex(sha.digest());   
		  
		 }
	//hash
	private static String byte2hex(byte[] b){
	     String hs="";
	     String stmp="";
	     for (int n=0;n<b.length;n++){
	      stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
	      if (stmp.length()==1) hs=hs+"0"+stmp;
	      else hs=hs+stmp;
	     }
	     return hs.toUpperCase();
	    }
}
