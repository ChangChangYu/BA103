package com.admPrilType.model;

import java.util.*;

import java.sql.*;

public class AdmPrilTypeJDBCDAO implements AdmPrilTypeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "servlet";
	String passwd = "123";

	private static final String INSERT_STMT = 
		"INSERT INTO admPrilType (admPrilID,admPrilTypeName,admPrilTypeStatus) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT admPrilID,admPrilTypeName,admPrilTypeStatus FROM admPrilType order by admPrilID";
	private static final String GET_ONE_STMT = 
		"SELECT admPrilID,admPrilTypeName,admPrilTypeStatus FROM admPrilType where admPrilID = ?";
	private static final String DELETE = 
		"DELETE FROM admPrilType where admPrilID = ?";
	private static final String UPDATE = 
		"UPDATE admPrilType set admPrilTypeName=?, admPrilTypeStatus=? where admPrilID = ?";
	@Override
	public void insert(AdmPrilTypeVO admPrilTypeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
				//admPrilID,admPrilTypeName,admPrilTypeStatus)
			pstmt.setInt(1, admPrilTypeVO.getAdmPrilID());
			pstmt.setString(2, admPrilTypeVO.getAdmPrilTypeName());
			pstmt.setInt(3, admPrilTypeVO.getAdmPrilTypeStatus());
		
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
	public void update(AdmPrilTypeVO admPrilTypeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admPrilTypeVO.getAdmPrilTypeName());
			pstmt.setInt(2, admPrilTypeVO.getAdmPrilTypeStatus());
			

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
	public void delete(Integer admPrilID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admPrilID);

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
	public AdmPrilTypeVO findByPrimaryKey(Integer admPrilID) {
		// TODO Auto-generated method stub
		AdmPrilTypeVO admPrilTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, admPrilID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				admPrilTypeVO = new AdmPrilTypeVO();
				admPrilTypeVO.setAdmPrilID(rs.getInt(1));
				admPrilTypeVO.setAdmPrilTypeName(rs.getString("ename"));
				admPrilTypeVO.setAdmPrilTypeStatus(rs.getInt(1));
				
				
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
		return admPrilTypeVO;
	}
	@Override
	public List<AdmPrilTypeVO> getAll() {
		// TODO Auto-generated method stub
		List<AdmPrilTypeVO> list = new ArrayList<AdmPrilTypeVO>();
		AdmPrilTypeVO admPrilTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				admPrilTypeVO = new AdmPrilTypeVO();
				admPrilTypeVO.setAdmPrilID(rs.getInt(1));
				admPrilTypeVO.setAdmPrilTypeName(rs.getString("ename"));
				admPrilTypeVO.setAdmPrilTypeStatus(rs.getInt(1));
				
				list.add(admPrilTypeVO); // Store the row in the list
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

		AdmPrilTypeJDBCDAO dao = new AdmPrilTypeJDBCDAO();

		// �s�W  admPrilID,admPrilTypeName,admPrilTypeStatus)
		AdmPrilTypeVO admPrilTypeVO1 = new AdmPrilTypeVO();
		admPrilTypeVO1.setAdmPrilID(7);
		admPrilTypeVO1.setAdmPrilTypeName("456");
		admPrilTypeVO1.setAdmPrilTypeStatus(1);

		dao.insert(admPrilTypeVO1);

		// �ק�
//		AdmPrilTypeVO admPrilTypeVO2 = new AdmPrilTypeVO();
//		admPrilTypeVO2.setAdmPrilID(1);
//		admPrilTypeVO2.setAdmPrilTypeName("�d�ç�1");
//		admPrilTypeVO2.setAdmPrilTypeStatus(1);
//		
//		dao.update(admPrilTypeVO2);
//
//		// �R��
//		dao.delete(1);

		// �d��
		AdmPrilTypeVO admPrilTypeVO3 = dao.findByPrimaryKey(1);
		System.out.print(admPrilTypeVO3.getAdmPrilID() + ",");
		System.out.print(admPrilTypeVO3.getAdmPrilTypeName() + ",");
		System.out.print(admPrilTypeVO3.getAdmPrilTypeStatus() + ",");
		
		System.out.println("---------------------");

		// �d��
		List<AdmPrilTypeVO> list = dao.getAll();
		for (AdmPrilTypeVO aAdmPrilType : list) {
			System.out.print(aAdmPrilType.getAdmPrilID() + ",");
			System.out.print(aAdmPrilType.getAdmPrilTypeName() + ",");
			System.out.print(aAdmPrilType.getAdmPrilTypeStatus() + ",");
			
			
			System.out.println();
		}
	}
}