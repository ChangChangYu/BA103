package com.club.model;

import java.util.*;

import com.clubMem.model.ClubMemVO;


public interface ClubDAO_interface {
          public void insert(ClubVO clubVO);
          public void update(ClubVO clubVO);
          public void delete(Integer clubID);
          public ClubVO findByPrimaryKey(Integer clubID);
          public List<ClubVO> getAll();
          //窾ノ狡琩高(肚把计篈Map)(肚 List)
          //public List<ClubVO> getAll(Map<String, String[]> map); 
	      //琩高琘场(癸)(肚 Set)
 //         public Set<ClubMemVO> getClubMemsByClubID(Integer clubID);
}
