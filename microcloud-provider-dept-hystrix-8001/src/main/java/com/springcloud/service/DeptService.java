package com.springcloud.service;

import java.util.List;

import com.springcloud.entity.Dept;


public interface DeptService {
	
	public boolean add(Dept dept);
	public Dept get(int id);
	public List<Dept> list();

}
