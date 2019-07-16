package com.springcloud.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.springcloud.entity.Dept;

@Mapper
public interface DeptDao {
	public boolean addDept(Dept dept);
	public Dept findById(int deptno);
	public List<Dept> findAll();
}
