package com.clubMem.model;

import java.util.List;

public interface ClubMemDAO_interface {

          public void insert(ClubMemVO clubMemVO);
          public void update(ClubMemVO clubMemVO);
//          public void delete(Integer memID,Integer clubID);
         public ClubMemVO findByPrimaryKey(Integer memID,Integer clubID);
         public List<ClubMemVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
	      
	      //查詢某部門的員工(一對多)(回傳 Set)
	      //public Set<EmpVO> getEmpsByDeptno(Integer deptno);	      
	      
	      
}
