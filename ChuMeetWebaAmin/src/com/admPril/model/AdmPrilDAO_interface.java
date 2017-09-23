package com.admPril.model;

import java.util.*;

public interface AdmPrilDAO_interface {
		public void insert(AdmPrilVO admPrilVO);
		public void update(AdmPrilVO admPrilVO);
		public void delete(Integer admPriladminID);
		public AdmPrilVO findByPrimaryKey(Integer admPriladminID);
		public List<AdmPrilVO> getAll();
		
}
