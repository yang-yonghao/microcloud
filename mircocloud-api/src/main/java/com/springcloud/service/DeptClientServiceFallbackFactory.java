package com.springcloud.service;
import java.util.List;
import org.springframework.stereotype.Component;
import com.springcloud.entity.Dept;
import feign.hystrix.FallbackFactory;

@Component //不要忘记添加
public class DeptClientServiceFallbackFactory implements DeptClientService {
//public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
	/*@Override
	public DeptClientService create(Throwable arg0) {
		return new DeptClientService() {
			@Override
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Dept get(int id) {
				return new Dept().setDeptno(id)
						.setDname("该ID："+id+"没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
						.setDbsource("no this database in mysql");
			}
			
			@Override
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}*/

	@Override
	public boolean add(Dept dept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dept get(int id) {
		return new Dept().setDeptno(id)
				.setDname("该ID："+id+"没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
				.setDbsource("no this database in mysql");
	}

	@Override
	public List<Dept> list() {
		// TODO Auto-generated method stub
		return null;
	}
}
