package com.Ann.model;
import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.admin.model.AdminDAO_interface;

public class AnnDAO implements AnnDAO_interface{
		
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
				"INSERT INTO ann (annID,adminID,annName,annContent,annDate) VALUES (annID_seq.NEXTVAL, ?, ?, ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT annID,adminID,annName,annContent,to_char(hiredate,'yyyy-mm-dd')annDate FROM ann order by annID";
			private static final String GET_ONE_STMT = 
				"SELECT annID,adminID,annName,annContent,to_char(hiredate,'yyyy-mm-dd')annDate FROM ann where annID = ?";
			private static final String DELETE = 
				"DELETE FROM ann where annID = ?";
			private static final String UPDATE = 
				"UPDATE ann set adminID=?,annName=?,annContent=?,annDate=?  where annID = ?";

			
			
			@Override
			public void insert(AnnVO annVO) {
				// TODO Auto-generated method stub
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);

					pstmt.setInt(1, annVO.getAdminID());
					pstmt.setString(2, annVO.getAnnName());
					pstmt.setString(3, annVO.getAnnContent());
					pstmt.setTimestamp(4, annVO.getAnnDate());
					

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
			public void update(AnnVO annVO) {
				// TODO Auto-generated method stub
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE);

					pstmt.setInt(1, annVO.getAdminID());
					pstmt.setString(2, annVO.getAnnName());
					pstmt.setString(3, annVO.getAnnContent());
					pstmt.setTimestamp(4, annVO.getAnnDate());
					pstmt.setInt(4, annVO.getAnnID());

					pstmt.executeUpdate();

					// Handle any driver errors
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
			public void delete(Integer annID) {
				// TODO Auto-generated method stub
				
				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE);

					pstmt.setInt(1, annID);

					pstmt.executeUpdate();

					// Handle any driver errors
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
			public AnnVO findByPrimaryKey(Integer annID) {
				// TODO Auto-generated method stub
				AnnVO annVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);

					pstmt.setInt(1, annID);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo �]�٬� Domain objects
						annVO = new AnnVO();
						annVO.setAnnID(rs.getInt(1));
						annVO.setAdminID(rs.getInt(1));
						annVO.setAnnContent(rs.getString("job"));
						annVO.setAnnDate(rs.getTimestamp("hiredate"));
						
						
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
				return annVO;
			}
			@Override
			public List<AnnVO> getAll() {
				// TODO Auto-generated method stub
				List<AnnVO> list = new ArrayList<AnnVO>();
				AnnVO annVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVO �]�٬� Domain objects
						annVO = new AnnVO();
						annVO.setAnnID(rs.getInt(1));
						annVO.setAdminID(rs.getInt(1));
						annVO.setAnnName(rs.getString("job"));
						annVO.setAnnContent(rs.getString("hiredate"));
						annVO.setAnnDate(rs.getTimestamp("sal"));
						
						list.add(annVO); // Store the row in the list
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

				AnnJDBCDAO dao = new AnnJDBCDAO();

				// �s�W
				AnnVO annVO1 = new AnnVO();
				annVO1.setAdminID(1);
				annVO1.setAnnName("MANAGER");
				annVO1.setAnnContent("MANAGER");
				annVO1.setAnnDate(java.sql.Timestamp.valueOf("2005-01-01"));
				
				dao.insert(annVO1);

				// �ק�
				AnnVO annVO2 = new AnnVO();
				annVO2.setAnnID(1);
				annVO2.setAdminID(1);
				annVO2.setAnnName("MANAGER2");
				annVO2.setAnnContent("MANAGER2");
				annVO2.setAnnDate(java.sql.Timestamp.valueOf("2002-01-01"));
				
				
				dao.update(annVO2);

				// �R��
				dao.delete(1);

				// �d��
				AnnVO annVO3 = dao.findByPrimaryKey(7001);
				System.out.print(annVO3.getAnnID() + ",");
				System.out.print(annVO3.getAdminID() + ",");
				System.out.print(annVO3.getAnnName() + ",");
				System.out.print(annVO3.getAnnContent() + ",");
				System.out.print(annVO3.getAnnDate() + ",");
			
				System.out.println("---------------------");

				// �d��
				List<AnnVO> list = dao.getAll();
				for (AnnVO aAnn : list) {
					System.out.print(aAnn.getAnnID() + ",");
					System.out.print(aAnn.getAdminID() + ",");
					System.out.print(aAnn.getAnnName() + ",");
					System.out.print(aAnn.getAnnContent() + ",");
					System.out.print(aAnn.getAnnDate() + ",");
				
					System.out.println();
				}
			}
			}