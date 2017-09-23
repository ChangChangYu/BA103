package com.admin.model;

import java.util.List;

public interface AdminDAO_interface {
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(Integer adminID);
	public AdminVO findByPrimaryKey(Integer adminID);
	public List<AdminVO> getAll();
	
}