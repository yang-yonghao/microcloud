package com.springcloud.service;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springcloud.entity.Dept;

//@FeignClient(value="MICROSERVICECLOUD-DEPT")
//@FeignClient(value="MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)//实现FallbackFactory接口
@FeignClient(value="MICROSERVICECLOUD-DEPT",fallback=DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
	
	@RequestMapping(value="/dept/add", method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept);
	
	@RequestMapping(value="/dept/get/{id}", method=RequestMethod.GET)
	public Dept get(@PathVariable("id") int id);
	
	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	public List<Dept> list();
}
