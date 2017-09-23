package com.admPril.model;
import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdmPrilDAO implements AdmPrilDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT=
			"INSERT INTO admPril (admPriladminID,admPrilID,admPrildate,admPrilStatus) VALUSE (?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT admPriladminID,admPrilID,to_char(hiredate,'yyyy-mm-dd')admPrildate,admPrilStatus From admPril order by admPriladminID";
	private static final String GET_ONE_STMT=
			"SELECT admPriladminID,admPrilID,to_char(hiredate,'yyyy-mm-dd')admPrildate,admPrilStatus From where admPriladminID";
	private static final String DELETE=
			"DELETE FROM admPril where admPriladminID =?";
	private static final String UPDATE=
			"UPDATE admPril set admPrilID=?, admPrildate=? ,admPrilStatus=?"; 
	



@Override
public void insert(AdmPrilVO admPrilVO) {
	
	// TODO Auto-generated method stub
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setInt(1, admPrilVO.getAdmPrilID());
		pstmt.setDate(2, admPrilVO.getAdmPrildate());
		pstmt.setInt(3, admPrilVO.getAdmPrilStatus());
	
		pstmt.executeUpdate();
	}  catch (SQLException se) {
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
public void update(AdmPrilVO admPrilVO) {
	// TODO Auto-generated method stub
	Connection con = null;
	PreparedStatement pstmt = null;

	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE);

		pstmt.setInt(1, admPrilVO.getAdmPrilID());
		pstmt.setDate(2, admPrilVO.getAdmPrildate());
		pstmt.setInt(3, admPrilVO.getAdmPrilStatus());
		
	
		pstmt.executeUpdate();
	}  catch (SQLException se) {
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
public void delete(Integer admPriladminID) {
	// TODO Auto-generated method stub
	Connection con = null;
	PreparedStatement pstmt = null;

	
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admPriladminID);

			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
public AdmPrilVO findByPrimaryKey(Integer admPriladminID) {
	// TODO Auto-generated method stub
	AdmPrilVO admPrilVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setInt(1, admPriladminID);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo �]�٬� Domain objects
			admPrilVO = new AdmPrilVO();
			admPrilVO.setAdmPriladminID(rs.getInt("admPriladminID"));
			admPrilVO.setAdmPrilID(rs.getInt("admPrilId"));
			admPrilVO.setAdmPrildate(rs.getDate("admPrildate"));
			admPrilVO.setAdmPrilStatus(rs.getInt("admPrilStatus"));
		
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
	return admPrilVO;
}
@Override
public List<AdmPrilVO> getAll() {
	// TODO Auto-generated method stub
	List<AdmPrilVO> list = new ArrayList<AdmPrilVO>();
	AdmPrilVO admPrilVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

	
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVO �]�٬� Domain objects
			admPrilVO = new AdmPrilVO();
			admPrilVO.setAdmPriladminID(rs.getInt("adminPriladminID"));
			admPrilVO.setAdmPrilID(rs.getInt("admPrilID"));
			admPrilVO.setAdmPrildate(rs.getDate("admPrildate"));
			admPrilVO.setAdmPrilStatus(rs.getInt("admPrilStatus"));
			
			list.add(admPrilVO); // Store the row in the list
		}

		// Handle any driver errors
	}  catch (SQLException se) {
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

	AdmPrilJDBCDAO dao = new AdmPrilJDBCDAO();

	// �s�W
	AdmPrilVO admPrilVO1 = new AdmPrilVO();
	admPrilVO1.setAdmPrilID(1);
	admPrilVO1.setAdmPrildate(java.sql.Date.valueOf("2005-01-01"));
	admPrilVO1.setAdmPrilStatus(1);
	
	dao.insert(admPrilVO1);

	// �ק�
	AdmPrilVO admPrilVO2 = new AdmPrilVO();
	admPrilVO2.setAdmPriladminID(1);
	admPrilVO2.setAdmPrilID(2);
	admPrilVO2.setAdmPrildate(java.sql.Date.valueOf("2005-01-01"));
	admPrilVO2.setAdmPrilStatus(2);
	dao.update(admPrilVO2);

	// �R��
	dao.delete(1);

	// �d��
	AdmPrilVO admPrilVO3 = dao.findByPrimaryKey(1);
	System.out.print(admPrilVO3.getAdmPriladminID() + ",");
	System.out.print(admPrilVO3.getAdmPrilID() + ",");
	System.out.print(admPrilVO3.getAdmPrildate() + ",");
	System.out.print(admPrilVO3.getAdmPrilStatus() + ",");
	
	System.out.println("---------------------");

	// �d��
	List<AdmPrilVO> list = dao.getAll();
	for (AdmPrilVO aAdmPril : list) {
		System.out.print(aAdmPril.getAdmPriladminID() + ",");
		System.out.print(aAdmPril.getAdmPrilID() + ",");
		System.out.print(aAdmPril.getAdmPrildate() + ",");
		System.out.print(aAdmPril.getAdmPrilStatus() + ",");
		
		System.out.println();
	}
}
}


