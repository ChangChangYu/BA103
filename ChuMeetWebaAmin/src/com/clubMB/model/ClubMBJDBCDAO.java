package com.clubMB.model;

import java.util.*;

import com.club.model.ClubJDBCDAO;
import com.club.model.ClubVO;

import java.sql.*;

public class ClubMBJDBCDAO implements ClubMBDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "testend";
	String passwd = "testend";

	private static final String INSERT_STMT = "INSERT INTO clubMB (clubMBID,clubID,memID,clubMBContent,clubMBDate,clubMBStatus) VALUES (clubMB_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT clubMBID,clubID,memID,clubMBContent,to_char(clubMBDate,'yyyy-mm-dd') clubMBDate,clubMBStatus FROM clubMB order by clubMBID";
	private static final String GET_ONE_STMT = "SELECT clubMBID,clubID,memID,clubMBContent,to_char(clubMBDate,'yyyy-mm-dd') clubMBDate,clubMBStatus FROM clubMB where clubMBID = ?";
	private static final String DELETE = "DELETE FROM clubMB where clubMBID = ?";
	private static final String UPDATE = "UPDATE clubMB set  clubID=?, memID=?, clubMBContent=?, clubMBDate=?, clubMBStatus=? where clubMBID = ?";
	
	private static final String GET_ONE_CLUB_MB_STMT = "SELECT clubMBID,clubID,memID,clubMBContent,to_char(clubMBDate,'yyyy-mm-dd') clubMBDate,clubMBStatus FROM clubMB where clubID = ?";

	@Override
	public void insert(ClubMBVO clubMBVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubMBVO.getClubID());
			pstmt.setInt(2, clubMBVO.getMemID());
			pstmt.setString(3, clubMBVO.getClubMBContent());
			pstmt.setDate(4, clubMBVO.getClubMBDate());
			pstmt.setInt(5, clubMBVO.getClubMBStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ClubMBVO clubMBVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubMBVO.getClubID());
			pstmt.setInt(2, clubMBVO.getMemID());
			pstmt.setString(3, clubMBVO.getClubMBContent());
			pstmt.setDate(4, clubMBVO.getClubMBDate());
			pstmt.setInt(5, clubMBVO.getClubMBStatus());
			pstmt.setInt(6, clubMBVO.getClubMBID());

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
	public void delete(Integer clubMBID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, clubMBID);

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
	public ClubMBVO findByPrimaryKey(Integer clubMBID) {
		ClubMBVO clubMBVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubMBID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				clubMBVO = new ClubMBVO();
				clubMBVO.setClubMBID(rs.getInt("clubMBID"));
				clubMBVO.setClubID(rs.getInt("clubID"));			
				clubMBVO.setMemID(rs.getInt("memID"));
				clubMBVO.setClubMBContent(rs.getString("clubMBContent"));
				clubMBVO.setClubMBDate(rs.getDate("clubMBDate"));
				clubMBVO.setClubMBStatus(rs.getInt("clubMBStatus"));
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
		return clubMBVO;
	}

	@Override
	public List<ClubMBVO> getAll() {
		List<ClubMBVO> list = new ArrayList<ClubMBVO>();
		ClubMBVO clubMBVO = null;

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
				clubMBVO = new ClubMBVO();
				clubMBVO.setClubMBID(rs.getInt("clubMBID"));
				clubMBVO.setClubID(rs.getInt("clubID"));
				clubMBVO.setMemID(rs.getInt("memID"));
				clubMBVO.setClubMBContent(rs.getString("clubMBContent"));
				clubMBVO.setClubMBDate(rs.getDate("clubMBDate"));
				clubMBVO.setClubMBStatus(rs.getInt("clubMBStatus"));
				list.add(clubMBVO); // Store the row in the list
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
	
	
	
	
	
	
	//以下自己加的
	//查詢單一社團全部留言
	@Override
	public List<ClubMBVO> findByClubID(Integer clubID) {
		List<ClubMBVO> list = new ArrayList<ClubMBVO>();
		ClubMBVO clubMBVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_CLUB_MB_STMT);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				clubMBVO = new ClubMBVO();
				clubMBVO.setClubMBID(rs.getInt("clubMBID"));
				clubMBVO.setClubID(rs.getInt("clubID"));
				clubMBVO.setMemID(rs.getInt("memID"));
				clubMBVO.setClubMBContent(rs.getString("clubMBContent"));
				clubMBVO.setClubMBDate(rs.getDate("clubMBDate"));
				clubMBVO.setClubMBStatus(rs.getInt("clubMBStatus"));
				list.add(clubMBVO); // Store the row in the list
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

		ClubMBJDBCDAO dao = new ClubMBJDBCDAO();

		// 新增ok
//		ClubMBVO clubMBVO1 = new ClubMBVO();
//		clubMBVO1.setClubID(1);
//		clubMBVO1.setMemID(1);
//		clubMBVO1.setClubMBContent("安安您好");
//		clubMBVO1.setClubMBDate(java.sql.Date.valueOf("2017-09-10"));
//		clubMBVO1.setClubMBStatus(1);
//		dao.insert(clubMBVO1);

		// 修改OK
//		 ClubMBVO clubMBVO2 = new ClubMBVO();
//		 clubMBVO2.setClubMBID(22);
//		 clubMBVO2.setClubID(1);
//		 clubMBVO2.setMemID(1);
//		 clubMBVO2.setClubMBContent("快來上課");
//		 clubMBVO2.setClubMBDate(java.sql.Date.valueOf("2017-09-10"));
//		 clubMBVO2.setClubMBStatus(1);
//		 dao.update(clubMBVO2);
		//
		// 刪除ok
//		 dao.delete(11);
		//
		// // 查詢OK
//		 ClubMBVO clubMBVO3 = dao.findByPrimaryKey(2);
//		 System.out.print(clubMBVO3.getClubMBID() + ",");
//		 System.out.print(clubMBVO3.getClubID() + ",");
//		 System.out.print(clubMBVO3.getMemID() + ",");
//		 System.out.print(clubMBVO3.getClubMBContent() + ",");
//		 System.out.print(clubMBVO3.getClubMBDate() + ",");
//		 System.out.println(clubMBVO3.getClubMBStatus());
//		 System.out.println("---------------------");
		//
		// 查詢OK
//		 List<ClubMBVO> list = dao.getAll();
//		 for (ClubMBVO aClub : list) {
//		 System.out.print(aClub.getClubMBID() + ",");
//		 System.out.print(aClub.getClubID() + ",");
//		 System.out.print(aClub.getMemID() + ",");
//		 System.out.print(aClub.getClubMBContent() + ",");
//		 System.out.print(aClub.getClubMBDate() + ",");
//		 System.out.print(aClub.getClubMBStatus());
//		 System.out.println();
//		 }
		
		
		//以下自己加的 
		// 查詢單一社團全部留言
		 List<ClubMBVO> list = dao.findByClubID(1);
		 for (ClubMBVO aClubMB : list) {
			 System.out.print(aClubMB.getClubMBID() + ",");
			 System.out.print(aClubMB.getClubID() + ",");
			 System.out.print(aClubMB.getMemID() + ",");
			 System.out.print(aClubMB.getClubMBContent() + ",");
			 System.out.print(aClubMB.getClubMBDate() + ",");
			 System.out.print(aClubMB.getClubMBStatus());
			 System.out.println();
		 }		
	}

}