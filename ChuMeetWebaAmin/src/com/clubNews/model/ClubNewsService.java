package com.clubNews.model;

import java.sql.Date;
import java.util.List;

public class ClubNewsService {

	private ClubNewsDAO_interface dao;

	public ClubNewsService() {
		dao = new ClubNewsDAO();
	}

	public ClubNewsVO addClubNews(Integer clubID, Integer memID, Date clubNewsDate, String clubNewsTitle, String clubNewsContent,
		Integer actID,Integer getClubNewsID){
		ClubNewsVO clubNewsVO = new ClubNewsVO();

		clubNewsVO.setClubID(clubID);
		clubNewsVO.setMemID(memID);
		clubNewsVO.setClubNewsDate(clubNewsDate);
		clubNewsVO.setClubNewsTitle(clubNewsTitle);
		clubNewsVO.setClubNewsContent(clubNewsContent);
		clubNewsVO.setActID(actID);
		dao.insert(clubNewsVO);

		return clubNewsVO;
	}


	public void addClubNews(ClubNewsVO clubNewsVO) {
		dao.insert(clubNewsVO);
	}
	
	public ClubNewsVO updateClubNews(Integer clubNewsID,Integer clubID, Integer memID, Date clubNewsDate, String clubNewsTitle, String clubNewsContent,
			Integer actID,Integer getClubNewsID) {

		ClubNewsVO clubNewsVO = new ClubNewsVO();
		clubNewsVO.setClubNewsID(clubNewsID);
		clubNewsVO.setClubID(clubID);
		clubNewsVO.setMemID(memID);
		clubNewsVO.setClubNewsDate(clubNewsDate);
		clubNewsVO.setClubNewsTitle(clubNewsTitle);
		clubNewsVO.setClubNewsContent(clubNewsContent);
		clubNewsVO.setActID(actID);
		dao.update(clubNewsVO);

		return dao.findByPrimaryKey(clubNewsID);
	}
	

	public void updateVlubNews(ClubNewsVO clubNewsVO) {
		dao.update(clubNewsVO);
	}

	public void deleteClubNews(Integer clubNewsID) {
		dao.delete(clubNewsID);
	}

	public ClubNewsVO getOneClubNews(Integer clubNewsID) {
		return dao.findByPrimaryKey(clubNewsID);
	}

	public List<ClubNewsVO> getAll() {
		return dao.getAll();
	}
}
