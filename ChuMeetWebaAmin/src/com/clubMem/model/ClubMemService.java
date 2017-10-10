package com.clubMem.model;

import java.util.List;
import java.util.Set;

import com.clubMem.model.ClubMemVO;

public class ClubMemService {

	private ClubMemDAO_interface dao;

	public ClubMemService() {
		dao = new ClubMemDAO();
	}
	
	public ClubMemVO addClubMem(Integer memID, Integer clubID,Integer clubMemType, java.sql.Date clubMemJoinDate) {

		ClubMemVO clubMemVO = new ClubMemVO();

		clubMemVO.setMemID(memID);
		clubMemVO.setClubID(clubID);
		clubMemVO.setClubMemType(clubMemType);
		clubMemVO.setClubMemJoinDate(clubMemJoinDate);
		dao.insert(clubMemVO);

		return clubMemVO;
	}
	
	//預留給 Struts 2 用的
	public void addClubMem(ClubMemVO clubMemVO) {
		dao.insert(clubMemVO);
	}
	
	public ClubMemVO updateClubMem(Integer memID, Integer clubID,Integer clubMemType, java.sql.Date clubMemJoinDate) {

		ClubMemVO clubMemVO = new ClubMemVO();

		clubMemVO.setMemID(memID);
		clubMemVO.setClubID(clubID);
		clubMemVO.setClubMemType(clubMemType);
		clubMemVO.setClubMemJoinDate(clubMemJoinDate);
		dao.update(clubMemVO);

		return dao.findByPrimaryKey(clubID,memID);
	}
	
	//預留給 Struts 2 用的
	public void updateClubMem(ClubMemVO clubMemVO) {
		dao.update(clubMemVO);
	}



	public ClubMemVO getOneClubMem(Integer clubID,Integer memID) {
		return dao.findByPrimaryKey(clubID,memID);
	}

	public List<ClubMemVO> getAll() {
		return dao.getAll();
	}
}
