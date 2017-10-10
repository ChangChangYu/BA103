package com.clubNews.model;

import java.util.*;

import com.club.model.ClubJDBCDAO;
import com.club.model.ClubVO;

import java.sql.*;

public class ClubNewsJDBCDAO implements ClubNewsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "testend";
	String passwd = "testend";

	private static final String INSERT_STMT = "INSERT INTO clubNews (clubNewsID,clubID,memID,clubNewsDate,clubNewsTitle,clubNewsContent,actID) VALUES (clubNews_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT clubNewsID,clubID,memID,to_char(clubNewsDate,'yyyy-mm-dd') clubNewsDate,clubNewsTitle,clubNewsContent,actID FROM clubNews order by clubNewsID";
	private static final String GET_ONE_STMT = "SELECT clubNewsID,clubID,memID,to_char(clubNewsDate,'yyyy-mm-dd') clubNewsDate,clubNewsTitle,clubNewsContent,actID FROM clubNews where clubNewsID = ?";
	private static final String DELETE = "DELETE FROM clubNews where clubNewsID = ?";
	private static final String UPDATE = "UPDATE clubNews set  clubID=?, memID=?, clubNewsDate=?, clubNewsTitle=?, clubNewsContent=?, actID=? where clubNewsID = ?";

	
	
	@Override
	public void insert(ClubNewsVO clubNewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubNewsVO.getClubID());
			pstmt.setInt(2, clubNewsVO.getMemID());
			pstmt.setDate(3, clubNewsVO.getClubNewsDate());
			pstmt.setString(4, clubNewsVO.getClubNewsTitle());
			pstmt.setString(5, clubNewsVO.getClubNewsContent());
			pstmt.setInt(6, clubNewsVO.getActID());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}




	@Override
	public void update(ClubNewsVO clubNewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubNewsVO.getClubID());
			pstmt.setInt(2, clubNewsVO.getMemID());
			pstmt.setDate(3, clubNewsVO.getClubNewsDate());
			pstmt.setString(4, clubNewsVO.getClubNewsTitle());
			pstmt.setString(5, clubNewsVO.getClubNewsContent());
			pstmt.setInt(6, clubNewsVO.getActID());
			pstmt.setInt(7, clubNewsVO.getClubNewsID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}




	@Override
	public void delete(Integer clubNewsID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, clubNewsID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}




	@Override
	public ClubNewsVO findByPrimaryKey(Integer clubNewsID) {
		ClubNewsVO clubNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubNewsID);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				clubNewsVO = new ClubNewsVO();
				clubNewsVO.setClubNewsID(rs.getInt("clubNewsID"));
				clubNewsVO.setClubID(rs.getInt("clubID"));			
				clubNewsVO.setMemID(rs.getInt("memID"));	
				clubNewsVO.setClubNewsDate(rs.getDate("clubNewsDate"));	
				clubNewsVO.setClubNewsTitle(rs.getString("clubNewsTitle"));
				clubNewsVO.setClubNewsContent(rs.getString("clubNewsContent"));
				clubNewsVO.setActID(rs.getInt("actID"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return clubNewsVO;
	}




	@Override
	public List<ClubNewsVO> getAll() {
		// TODO Auto-generated method stub
		List<ClubNewsVO> list = new ArrayList<ClubNewsVO>();
		ClubNewsVO clubNewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				clubNewsVO = new ClubNewsVO();
				clubNewsVO.setClubNewsID(rs.getInt("clubNewsID"));
				clubNewsVO.setClubID(rs.getInt("clubID"));
				clubNewsVO.setMemID(rs.getInt("memID"));
				clubNewsVO.setClubNewsDate(rs.getDate("clubNewsDate"));
				clubNewsVO.setClubNewsTitle(rs.getString("clubNewsTitle"));
				clubNewsVO.setClubNewsContent(rs.getString("clubNewsContent"));
				clubNewsVO.setActID(rs.getInt("actID"));

				list.add(clubNewsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	


	public static void main(String[] args) {

		ClubNewsJDBCDAO dao = new ClubNewsJDBCDAO();

		// 新增OK
		ClubNewsVO clubNewsVO1 = new ClubNewsVO();
		clubNewsVO1.setClubID(1);
		clubNewsVO1.setMemID(1);
		clubNewsVO1.setClubNewsDate(java.sql.Date.valueOf("2017-09-10"));
		clubNewsVO1.setClubNewsTitle("早安");
		clubNewsVO1.setClubNewsContent("安安您好");
		clubNewsVO1.setActID(1);
		dao.insert(clubNewsVO1);

		// 修改OK
//		 ClubNewsVO clubNewsVO2 = new ClubNewsVO();
//		 clubNewsVO2.setActID(1);	 
//		 clubNewsVO2.setClubNewsID(11);
//		 clubNewsVO2.setClubID(2);
//		 clubNewsVO2.setMemID(2);
//		 clubNewsVO2.setClubNewsDate(java.sql.Date.valueOf("2017-09-11"));
//		 clubNewsVO2.setClubNewsTitle("早安2");
//		 clubNewsVO2.setClubNewsContent("安安您好2");
//		 dao.update(clubNewsVO2);
		
		// 刪除OK
//		 dao.delete(11);
		//
		
		// // 查詢OK
//		 ClubNewsVO clubNewsVO3 = dao.findByPrimaryKey(2);
//		 System.out.print(clubNewsVO3.getClubNewsID() + ",");
//		 System.out.print(clubNewsVO3.getClubID() + ",");
//		 System.out.print(clubNewsVO3.getMemID() + ",");
//		 System.out.print(clubNewsVO3.getClubNewsDate() + ",");
//		 System.out.print(clubNewsVO3.getClubNewsTitle() + ",");
//		 System.out.print(clubNewsVO3.getClubNewsContent() + ",");
//		 System.out.println(clubNewsVO3.getActID());
//		 System.out.println("---------------------");
		//
		
		// 查詢OK
//		 List<ClubNewsVO> list = dao.getAll();
//		 for (ClubNewsVO aClub : list) {
//		 System.out.print(aClub.getClubNewsID() + ",");
//		 System.out.print(aClub.getClubID() + ",");
//		 System.out.print(aClub.getMemID() + ",");
//		 System.out.print(aClub.getClubNewsDate() + ",");
//		 System.out.print(aClub.getClubNewsTitle() + ",");
//		 System.out.print(aClub.getClubNewsContent() + ",");
//		 System.out.print(aClub.getActID());
//		 System.out.println();
//		 }
		
		
	}






}