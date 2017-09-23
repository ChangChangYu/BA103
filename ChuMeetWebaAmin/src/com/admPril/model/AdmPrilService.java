package com.admPril.model;
import java.util.List;


public class AdmPrilService {

	
	private AdmPrilDAO_interface dao;

	public AdmPrilService() {
		dao = new AdmPrilDAO();
	}

	public AdmPrilVO addAdmPril(Integer admPrilID,
			java.sql.Date admPrildate, Integer admPrilStatus) {

		AdmPrilVO admPrilVO = new AdmPrilVO();

		admPrilVO.setAdmPrilID(admPrilID);
		admPrilVO.setAdmPrildate(admPrildate);
		admPrilVO.setAdmPrilStatus(admPrilStatus);
		dao.insert(admPrilVO);

		return admPrilVO;
	}

	//�w�d�� Struts 2 �Ϊ�
	public void addAdmPril(AdmPrilVO admPrilVO) {
		dao.insert(admPrilVO);
	}
	
	public AdmPrilVO updateAdmPril(Integer admPriladminID, Integer admPrilID,
			java.sql.Date admPrildate, Integer admPrilStatus) {

		AdmPrilVO admPrilVO = new AdmPrilVO();

		admPrilVO.setAdmPriladminID(admPriladminID);
		admPrilVO.setAdmPrilID(admPrilID);
		admPrilVO.setAdmPrildate(admPrildate);
		admPrilVO.setAdmPrilStatus(admPrilStatus);
		
		dao.update(admPrilVO);

		return dao.findByPrimaryKey(admPrilID);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateAdmPril(AdmPrilVO admPrilVO) {
		dao.update(admPrilVO);
	}

	public void deleteAdmPril(Integer admPrilID) {
		dao.delete(admPrilID);
	}

	public AdmPrilVO getOneAdmPril(Integer admPrilID) {
		return dao.findByPrimaryKey(admPrilID);
	}

	public List<AdmPrilVO> getAll() {
		return dao.getAll();
	}
}


