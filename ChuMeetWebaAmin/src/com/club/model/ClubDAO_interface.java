package com.club.model;

import java.util.*;

import com.clubMem.model.ClubMemVO;


public interface ClubDAO_interface {
          public void insert(ClubVO clubVO);
          public void update(ClubVO clubVO);
          public void delete(Integer clubID);
          public ClubVO findByPrimaryKey(Integer clubID);
          public List<ClubVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
          //public List<ClubVO> getAll(Map<String, String[]> map); 
	      //�d�߬Y���������u(�@��h)(�^�� Set)
 //         public Set<ClubMemVO> getClubMemsByClubID(Integer clubID);
}
