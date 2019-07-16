package com.myrule;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class MyRandomRule extends AbstractLoadBalancerRule {
	//当total==5以后，指针才能往下走
	//currentIndex 当前对外提供服务的服务器地址下标
	private int total = 0; //总共被调用的次数，目前要求每台被调用5次
	private int currentIndex = 0; //当前提供服务的机器号

    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            
            if(total < 5) {
            	total ++ ;
            	server = lb.getAllServers().get(currentIndex);
            }else {
            	total = 0;
            	currentIndex ++ ;
            	if(currentIndex >= lb.getAllServers().size()) {
            		currentIndex = 0;
            	}
            }
            
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig arg0) {
		// TODO Auto-generated method stub
	}
}