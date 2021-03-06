package com.club.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ClubDAO implements ClubDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO club (clubID,clubmemID,clubName,clubTypeID,clubContent,clubPhoto,clubStartDate,clubStatus,clubLong,clubLat) VALUES (club_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT clubID,clubmemID,clubName,clubTypeID,clubContent,clubPhoto,to_char(clubStartDate,'yyyy-mm-dd') clubStartDate,clubStatus,clubLong,clubLat FROM club order by clubID";
		private static final String GET_ONE_STMT = 
			"SELECT clubID,clubmemID,clubName,clubTypeID,clubContent,clubPhoto,to_char(clubStartDate,'yyyy-mm-dd') clubStartDate,clubStatus,clubLong,clubLat FROM club where clubID = ?";
		private static final String DELETE = 
			"DELETE FROM club where clubID = ?";
		private static final String UPDATE = 
			"UPDATE club set clubmemID=?, clubName=?, clubTypeID=?, clubContent=?, clubPhoto=?, clubStartDate=?, clubStatus=?, clubLong=?, clubLat=? where clubID = ?";

	@Override
	public void insert(ClubVO clubVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubVO.getClubmemID());
			pstmt.setString(2, clubVO.getClubName());
			pstmt.setInt(3, clubVO.getClubTypeID());
			pstmt.setString(4, clubVO.getClubContent());
			
			pstmt.setBytes(5,clubVO.getClubPhoto());
			
			pstmt.setDate(6, clubVO.getClubStartDate());
			pstmt.setInt(7, clubVO.getClubStatus());
			pstmt.setDouble(8, clubVO.getClubLong());
			pstmt.setDouble(9, clubVO.getClubLat());

			pstmt.executeUpdate();

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
	public void update(ClubVO clubVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubVO.getClubmemID());
			pstmt.setString(2, clubVO.getClubName());
			pstmt.setInt(3, clubVO.getClubTypeID());
			pstmt.setString(4, clubVO.getClubContent());
			
			pstmt.setBytes(5, clubVO.getClubPhoto());
			
			pstmt.setDate(6, clubVO.getClubStartDate());
			pstmt.setInt(7, clubVO.getClubStatus());
			pstmt.setDouble(8, clubVO.getClubLong());
			pstmt.setDouble(9, clubVO.getClubLat());
			pstmt.setInt(10, clubVO.getClubID());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer clubID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, clubID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ClubVO findByPrimaryKey(Integer clubID) {

		ClubVO clubVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				clubVO = new ClubVO();
				clubVO.setClubID(rs.getInt("clubID"));
				clubVO.setClubmemID(rs.getInt("clubmemID"));			
				clubVO.setClubName(rs.getString("clubName"));
				clubVO.setClubTypeID(rs.getInt("clubTypeID"));
				clubVO.setClubContent(rs.getString("clubContent"));
				
				clubVO.setClubPhoto(rs.getBytes("clubPhoto"));
				
				clubVO.setClubStartDate(rs.getDate("clubStartDate"));
				clubVO.setClubStatus(rs.getInt("clubStatus"));
				clubVO.setClubLong(rs.getDouble("clubLong"));
				clubVO.setClubLat(rs.getDouble("clubLat"));
			}

			// Handle any driver errors
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
		return clubVO;
	}

	@Override
	public List<ClubVO> getAll() {
		List<ClubVO> list = new ArrayList<ClubVO>();
		ClubVO clubVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				clubVO = new ClubVO();
				clubVO.setClubID(rs.getInt("clubID"));
				clubVO.setClubmemID(rs.getInt("clubMemID"));
				clubVO.setClubName(rs.getString("clubName"));
				clubVO.setClubTypeID(rs.getInt("clubTypeID"));
				clubVO.setClubContent(rs.getString("clubContent"));
				
				clubVO.setClubPhoto(rs.getBytes("clubPhoto"));
				
				clubVO.setClubStartDate(rs.getDate("clubStartDate"));
				clubVO.setClubStatus(rs.getInt("clubStatus"));
				clubVO.setClubLong(rs.getDouble("clubLong"));
				clubVO.setClubLat(rs.getDouble("clubLat"));
				list.add(clubVO); 
				// Store the row in the list
			}

			// Handle any driver errors
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
	
	
	
//測試用	
	public static void main(String[] args) throws IOException {

		ClubJDBCDAO dao = new ClubJDBCDAO();

		// 新增OK
		ClubVO clubVO1 = new ClubVO();
		clubVO1.setClubmemID(7);
		clubVO1.setClubName("張宏印");
		clubVO1.setClubTypeID(1);
		clubVO1.setClubContent("安安您好");
		
		byte[] pic1=getByteArray("C:\\Users\\simpl\\Desktop\\ChuMeetＷebsite\\club\\image\\music.jpg");
		System.out.println(pic1.length);
		clubVO1.setClubPhoto(pic1);
		
		clubVO1.setClubStartDate(java.sql.Date.valueOf("2017-09-09"));
		clubVO1.setClubStatus(1);
		clubVO1.setClubLong(1.00006);
		clubVO1.setClubLat(1.000007);
		dao.insert(clubVO1);

//		// 修改OK
//		ClubVO clubVO2 = new ClubVO();
//		clubVO2.setClubID(42);
//		clubVO2.setClubmemID(9);
//		clubVO2.setClubName("吳神JAVA");
//		clubVO2.setClubTypeID(1);
//		clubVO2.setClubContent("親自授課");
//		
//		byte[] pic1=getByteArray("C:\\Users\\simpl\\Desktop\\ChuMeetＷebsite\\club\\image\\music.jpg");
//		System.out.println(pic1.length);
//		clubVO2.setClubPhoto(pic1);
//		
//		clubVO2.setClubStartDate(java.sql.Date.valueOf("2017-09-09"));
//		clubVO2.setClubStatus(1);
//		clubVO2.setClubLong(19.1131131);
//		clubVO2.setClubLat(20.121416);
//		dao.update(clubVO2);
////
//		// 刪除
////		dao.delete(43);
////
//		// 查詢OK
//		ClubVO clubVO3 = dao.findByPrimaryKey(2);
//		System.out.print(clubVO3.getClubID() + ",");
//		System.out.print(clubVO3.getClubmemID() + ",");
//		System.out.print(clubVO3.getClubName() + ",");
//		System.out.print(clubVO3.getClubTypeID() + ",");
//		System.out.print(clubVO3.getClubContent() + ",");
//		System.out.print(clubVO3.getClubPhoto() + ",");
//		System.out.print(clubVO3.getClubStartDate() + ",");
//		System.out.print(clubVO3.getClubStatus() + ",");
//		System.out.print(clubVO3.getClubLong() + ",");
//		System.out.println(clubVO3.getClubLat());
//		System.out.println("---------------------");
//
//		// 查詢OK
//		List<ClubVO> list = dao.getAll();
//		for (ClubVO aClub : list) {
//			System.out.print(aClub.getClubID() + ",");
//			System.out.print(aClub.getClubmemID() + ",");
//			System.out.print(aClub.getClubName() + ",");
//			System.out.print(aClub.getClubTypeID() + ",");
//			System.out.print(aClub.getClubContent() + ",");
//		    System.out.print(aClub.getClubPhoto() + ",");
//			System.out.print(aClub.getClubStartDate() + ",");
//			System.out.print(aClub.getClubStatus() + ",");
//			System.out.print(aClub.getClubLong() + ",");
//			System.out.print(aClub.getClubLat());
//			System.out.println();
//		}
	}

	
	public static byte[] getByteArray(String path) throws IOException {
		FileInputStream fin=new FileInputStream(new File(path));
		byte [] buffer=new byte[fin.available()];
		int bytes_read;
		while((bytes_read=fin.read(buffer))!=-1){   //-1代表游標有往下移,有東西
		}
		return buffer;
		
		
		
	}

	
	
	
	
	
}