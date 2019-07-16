package com.springcloud.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.entity.Dept;
import com.springcloud.service.DeptService;

@RestController
public class DeptController {

	@Autowired
	private DeptService service;
	
	@RequestMapping(value="/dept/get/{id}", method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod="processHystrix_Get")
	public Dept get(@PathVariable("id") int id) {
		Dept dept = service.get(id);
		if(dept == null) {
			throw new RuntimeException("该ID："+id+"没有相应的信息");
		}
		return dept;
	}
	
	public Dept processHystrix_Get(@PathVariable("id") int id) {
		return new Dept().setDeptno(id).setDname("该ID："+id+"没有相应的信息,null--@HystrixCommand").setDbsource("no this database in mysql");
	}
}
