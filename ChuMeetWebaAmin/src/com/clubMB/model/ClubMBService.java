package com.clubMB.model;

import java.util.List;

public class ClubMBService {

	private ClubMBDAO_interface dao;

	public ClubMBService() {
		dao = new ClubMBDAO();
	}

	public ClubMBVO addClubMB(Integer clubMBID, Integer clubID,Integer memID,String clubMBContent, java.sql.Date clubMBDate,
			Integer clubMBStatus) {

		ClubMBVO clubMBVO = new ClubMBVO();

		clubMBVO.setClubMBID(clubMBID);
		clubMBVO.setClubID(clubID);
		clubMBVO.setMemID(memID);
		clubMBVO.setClubMBContent(clubMBContent);
		clubMBVO.setClubMBDate(clubMBDate);
		clubMBVO.setClubMBStatus(clubMBStatus);
		dao.insert(clubMBVO);

		return clubMBVO;
	}

	//預留給 Struts 2 用的
	public void addClubMB(ClubMBVO clubMBVO) {
		dao.insert(clubMBVO);
	}
	
	public ClubMBVO updateClubMB(Integer clubMBID,Integer clubID, Integer memID,String clubMBContent, java.sql.Date clubMBDate,
			Integer clubMBStatus) {

		ClubMBVO clubMBVO = new ClubMBVO();

		clubMBVO.setClubMBID(clubMBID);
		clubMBVO.setClubID(clubID);
		clubMBVO.setMemID(memID);
		clubMBVO.setClubMBContent(clubMBContent);
		clubMBVO.setClubMBDate(clubMBDate);
		clubMBVO.setClubMBStatus(clubMBStatus);
		dao.update(clubMBVO);

		return dao.findByPrimaryKey(clubMBID);
	}
	
	//預留給 Struts 2 用的
	public void updateClubMB(ClubMBVO clubMBVO) {
		dao.update(clubMBVO);
	}

	public void deleteClubMB(Integer clubMBID) {
		dao.delete(clubMBID);
	}

	public ClubMBVO getOneClubMB(Integer clubMBID) {
		return dao.findByPrimaryKey(clubMBID);
	}

	public List<ClubMBVO> getAll() {
		return dao.getAll();
	}
}
