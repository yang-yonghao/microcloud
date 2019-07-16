package com.springcloud.cfgbeans;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean { //boot -> spring @Configuration -> applicationContext.xml
	//@Bean -> <bean id="userService" class="com.springcloud.UserServiceImpl">
	@Bean
	@LoadBalanced //SpringCloud Ribbon是基于Netflix Ribbon实现的一套客户端 负载均衡工具
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	//如果不加下面的指定算法，默认使用轮询算法
	@Bean
	public IRule myRule() {
		//return new RoundRobinRule(); //默认轮询算法
		//return new RandomRule(); //随机算法替换默认的轮询算法
		return new RetryRule();
	}
}
