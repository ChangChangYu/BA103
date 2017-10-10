package com.clubAlbum.model;

import java.util.List;

import com.clubAlbum.model.ClubAlbumVO;

public class ClubAlbumService {

	private ClubAlbumDAO_interface dao;

	public ClubAlbumService() {
		dao = new ClubAlbumDAO();
	}

	//Integer clubAlbumID,Integer clubID,Integer memID,java.sql.Date clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus
	//
	
	public ClubAlbumVO addClubAlbum(Integer clubID,
			Integer memID,java.sql.Date clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus) {

		ClubAlbumVO clubAlbumVO = new ClubAlbumVO();
		clubAlbumVO.setClubID(clubID);
		clubAlbumVO.setMemID(memID);
		clubAlbumVO.setClubAlbumDate(clubAlbumDate);
		clubAlbumVO.setClubAlbumName(clubAlbumName);
		clubAlbumVO.setClubAlbumStatus(clubAlbumStatus);

		dao.insert(clubAlbumVO);

		return clubAlbumVO;
	}


	
	//Integer clubAlbumID,Integer clubID,Integer memID,java.sql.Date clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus
	//
	
	public ClubAlbumVO updateClubAlbumVO(Integer clubAlbumID,Integer clubID,Integer memID,java.sql.Date clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus) {

		ClubAlbumVO clubAlbumVO = new ClubAlbumVO();
		clubAlbumVO.setClubAlbumID(clubAlbumID);
		clubAlbumVO.setClubID(clubID);
		clubAlbumVO.setMemID(memID);
		clubAlbumVO.setClubAlbumDate(clubAlbumDate);
		clubAlbumVO.setClubAlbumName(clubAlbumName);
		clubAlbumVO.setClubAlbumStatus(clubAlbumStatus);
		dao.update(clubAlbumVO);

		return clubAlbumVO;
	}

//	public void deleteClubAlbum(Integer clubAlbumVO) {
//		dao.delete(clubAlbumVO);
//	}

	public ClubAlbumVO getOneClubAlbum(Integer clubAlbumVO) {
		return dao.findByPrimaryKey(clubAlbumVO);
	}

	public List<ClubAlbumVO> getAll() {
		return dao.getAll();
	}
}
