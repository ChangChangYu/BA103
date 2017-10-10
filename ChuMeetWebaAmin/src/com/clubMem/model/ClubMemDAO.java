package com.clubMem.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.clubMem.model.ClubMemVO;

public class ClubMemDAO implements ClubMemDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO clubMem (clubID,memID,clubMemType,clubMemJoinDate) VALUES ( ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT clubID, memID , clubMemType,clubMemJoinDate FROM clubMem";
	private static final String GET_ONE_STMT = "SELECT memID , clubID, clubMemType, clubMemJoinDate FROM clubMem where clubID = ? and memID = ?";
	private static final String UPDATE = "UPDATE clubMem set clubMemType=? ,clubMemJoinDate=?  where clubID = ? and memID = ?";

	
	@Override
	public void insert(ClubMemVO clubMemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubMemVO.getClubID());
			pstmt.setInt(2, clubMemVO.getMemID());
			pstmt.setInt(3, clubMemVO.getClubMemType());
			pstmt.setDate(4, clubMemVO.getClubMemJoinDate());
			pstmt.executeUpdate();

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
	public void update(ClubMemVO clubMemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubMemVO.getClubMemType());
			pstmt.setDate(2, clubMemVO.getClubMemJoinDate());
			pstmt.setInt(3, clubMemVO.getClubID());
			pstmt.setInt(4, clubMemVO.getMemID());

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
	public ClubMemVO findByPrimaryKey(Integer clubID,Integer memID) {

		ClubMemVO clubMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubID);
			pstmt.setInt(2, memID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				clubMemVO = new ClubMemVO();
				clubMemVO.setMemID(rs.getInt("clubID"));
				clubMemVO.setClubID(rs.getInt("memID"));
				clubMemVO.setClubMemType(rs.getInt("clubMemType"));
				clubMemVO.setClubMemJoinDate(rs.getDate("clubMemJoinDate"));
			}

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
		return clubMemVO;
	}

	@Override
	public List<ClubMemVO> getAll() {
		List<ClubMemVO> list = new ArrayList<ClubMemVO>();
		ClubMemVO clubMemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				clubMemVO = new ClubMemVO();
				clubMemVO.setClubID(rs.getInt("clubID"));
				clubMemVO.setMemID(rs.getInt("memID"));
				clubMemVO.setClubMemType(rs.getInt("clubMemType"));
				clubMemVO.setClubMemJoinDate(rs.getDate("clubMemJoinDate"));
				list.add(clubMemVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

//	@Override
//	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
//		Set<EmpVO> set = new LinkedHashSet<EmpVO>();
//		EmpVO empVO = null;
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//	
//		try {
//	
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_Emps_ByDeptno_STMT);
//			pstmt.setInt(1, deptno);
//			rs = pstmt.executeQuery();
//	
//			while (rs.next()) {
//				empVO = new EmpVO();
//				empVO.setEmpno(rs.getInt("empno"));
//				empVO.setEname(rs.getString("ename"));
//				empVO.setJob(rs.getString("job"));
//				empVO.setHiredate(rs.getDate("hiredate"));
//				empVO.setSal(rs.getDouble("sal"));
//				empVO.setComm(rs.getDouble("comm"));
//				empVO.setDeptno(rs.getInt("deptno"));
//				set.add(empVO); // Store the row in the vector
//			}
//	
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return set;
//	}
	
	
	
	
	
	public static void main(String[] args) {

		ClubMemJDBCDAO dao = new ClubMemJDBCDAO();

		// 新增OK
//		ClubMemVO clubMemVO1 = new ClubMemVO();
//		clubMemVO1.setClubID(7);
//		clubMemVO1.setMemID(4);
//		clubMemVO1.setClubMemType(1);
//		clubMemVO1.setClubMemJoinDate(java.sql.Date.valueOf("2017-09-12"));
//		dao.insert(clubMemVO1);

		
		
		// 修改OK
//		ClubMemVO clubMemVO2 = new ClubMemVO();
//		clubMemVO2.setClubMemType(2);
//		clubMemVO2.setClubMemJoinDate(java.sql.Date.valueOf("2017-07-30"));
//		clubMemVO2.setClubID(7);
//		clubMemVO2.setMemID(4);
//		dao.update(clubMemVO2);


		// 刪除還沒做
		//dao.delete(30);

		// 查詢OK
//		ClubMemVO clubMemVO3 = dao.findByPrimaryKey(1,1);
//		System.out.print(clubMemVO3.getClubID() + ",");
//		System.out.print(clubMemVO3.getMemID() + ",");
//		System.out.println(clubMemVO3.getClubMemType()+ ",");
//		System.out.println(clubMemVO3.getClubMemJoinDate());		
//		System.out.println("---------------------");

		// 查詢部門OK
//		List<ClubMemVO> list = dao.getAll();
//		for (ClubMemVO aClubMem : list) {
//			System.out.print(aClubMem.getClubID() + ",");
//			System.out.print(aClubMem.getMemID() + ",");
//			System.out.print(aClubMem.getClubMemType() + ",");
//			System.out.print(aClubMem.getClubMemJoinDate());
//			System.out.println();
//		}


		
		// 查詢某部門的員工還沒
//		Set<EmpVO> set = dao.getEmpsByDeptno(10);
//		for (EmpVO aEmp : set) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptno());
//			System.out.println();
//		}
	
	
	}
}
	
	
	
	
	
	
	
