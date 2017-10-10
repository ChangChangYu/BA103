package com.club.model;

import java.util.List;

public class ClubService {

	private ClubDAO_interface dao;

	public ClubService() {
		dao = new ClubDAO();
	}
	
	
	//可刪除Integer clubID,Integer clubmemID, String clubName,Integer clubTypeID,String clubContent,byte[]clubPhoto, java.sql.Date clubStartDate,Integer clubStatus,Double clubLong, Double clubLat
	//

	public ClubVO addClub(Integer clubmemID, String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto,
			java.sql.Date clubStartDate,Integer clubStatus,Double clubLong, Double clubLat) {

		ClubVO clubVO = new ClubVO();

		clubVO.setClubmemID(clubmemID);
		clubVO.setClubName(clubName);
		clubVO.setClubTypeID(clubTypeID);
		clubVO.setClubContent(clubContent);
		clubVO.setClubPhoto(clubPhoto);
		clubVO.setClubStartDate(clubStartDate);
		clubVO.setClubStatus(clubStatus);
		clubVO.setClubLong(clubLong);
		clubVO.setClubLat(clubLat);
		dao.insert(clubVO);

		return clubVO;
	}

	//可刪除Integer clubID,Integer clubmemID, String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto,java.sql.Date clubStartDate,Integer clubStatus,Double clubLong, Double clubLat
	//
	public ClubVO updateClub(Integer clubID,Integer clubmemID, String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto,
	java.sql.Date clubStartDate,Integer clubStatus,Double clubLong, Double clubLat) {

		ClubVO clubVO = new ClubVO();
		clubVO.setClubID(clubID);
		clubVO.setClubmemID(clubmemID);
		clubVO.setClubName(clubName);
		clubVO.setClubTypeID(clubTypeID);
		clubVO.setClubContent(clubContent);
		clubVO.setClubPhoto(clubPhoto);
		clubVO.setClubStartDate(java.sql.Date.valueOf("2017-09-09"));
		clubVO.setClubStatus(clubStatus);
		clubVO.setClubLong(clubLong);
		clubVO.setClubLat(clubLat);
		dao.update(clubVO);

		return clubVO;
	}

	public void deleteEmp(Integer clubID) {
		dao.delete(clubID);
	}

	public ClubVO getOneClub(Integer clubID) {
		
		return dao.findByPrimaryKey(clubID);
	}

	public List<ClubVO> getAll() {
		return dao.getAll();
	}
}
