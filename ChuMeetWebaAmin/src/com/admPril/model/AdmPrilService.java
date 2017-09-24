package com.admPril.model;
import java.util.List;


public class AdmPrilService {

	
	private AdmPrilDAO_interface dao;

	public AdmPrilService() {
		dao = new AdmPrilDAO();
	}

	public AdmPrilVO addAdmPril(Integer adminID,
			java.sql.Timestamp admPrildate, Integer admPrilStatus) {

		AdmPrilVO admPrilVO = new AdmPrilVO();

		admPrilVO.setAdminID(adminID);
		admPrilVO.setAdmPrildate(admPrildate);
		admPrilVO.setAdmPrilStatus(admPrilStatus);
		dao.insert(admPrilVO);

		return admPrilVO;
	}

	//�w�d�� Struts 2 �Ϊ�
	public void addAdmPril(AdmPrilVO admPrilVO) {
		dao.insert(admPrilVO);
	}
	
	public AdmPrilVO updateAdmPril(Integer admPrilID, Integer adminID,
			java.sql.Timestamp admPrildate, Integer admPrilStatus) {

		AdmPrilVO admPrilVO = new AdmPrilVO();

		admPrilVO.setAdmPrilID(admPrilID);
		admPrilVO.setAdminID(adminID);
		admPrilVO.setAdmPrildate(admPrildate);
		admPrilVO.setAdmPrilStatus(admPrilStatus);
		
		dao.update(admPrilVO);

		return dao.findByPrimaryKey(adminID);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateAdmPril(AdmPrilVO admPrilVO) {
		dao.update(admPrilVO);
	}

	public void deleteAdmPril(Integer adminID) {
		dao.delete(adminID);
	}

	public AdmPrilVO getOneAdmPril(Integer adminID) {
		return dao.findByPrimaryKey(adminID);
	}

	public List<AdmPrilVO> getAll() {
		return dao.getAll();
	}
}


