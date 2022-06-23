package com.tyc.provider2.service.dubbo;

import com.tyc.api.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.ConsistentHashLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.LeastActiveLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;
import org.apache.dubbo.rpc.cluster.support.FailoverCluster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.zookeeper.discovery.dependency.LoadBalancerType;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-21 13:35:15
 */
//

/**
 * dubbo2.7.8版本为了与spring区分开 改为 DubboService
 * cluster 6种容错模式
 *      failovre: 失败后调用集群中其它机器重试，默认重试2次(retries = 2)，常用于读操作
 *      failfast: 服务调用失败立即报错，常用于修改操作，可能数据修改成功但网络延时导致响应失败
 *      failsafe: 出现异常时忽略异常
 *      failback: 失败后记录下来 定时重发 适用于消息通知
 *      forking:  并行调用集群中多个服务，只要其中一个成功就返回
 *      broadcast: 广播调用所有服务提供者，任意一个失败则表示调用失败 适用于通知所有服务更新缓存等信息
 * loadbalance 4种负载均衡策略
 *      random(默认): 随机算法，服务器权重越大，随机概率越大
 *      roundrobin: 轮询按照公约后的权重轮询
 *      leastactive: 处理慢的节点收到请求少
 *      consistenthash: 一致性hash
 * 服务降级：流量大的时候把非核心业务暂时关闭 例如双11把查询历史订单等功能暂时关闭
 * 降级不同层面分类：自动降级与人工降级  读服务降级与写服务降级
 *      故障降级：远程服务挂了 设置兜底数据返回而不是报错
 *      限流降级：流量达到阈值，请求进入排队系统，直接返回用户 “活动太火爆 稍后再来”页面
 * 主机绑定规则：dubbo服务对外发布的IP地址(即写入注册中心的IP地址) 默认按照以下规则查找绑定主机IP地址
 *      1.查找环境变量 DUBBO_IP_TO_BIND 的值 没有则往下
 *      2.配置文件 dubbo.protocol.host 值 没有则往下
 *      3.通过LocalHost.getHostAddress 获取本机IP地址 失败则继续往下
 *      4.如果配置了注册中心的地址，则使用Socket通信连接到注册中心后，for循环socket.getLocalAddress().getHostAddress()扫描各个网卡获取网卡IP地址
  */
@DubboService(cluster = FailoverCluster.NAME,retries = 3,loadbalance = ConsistentHashLoadBalance.NAME)
public class HelloServiceImpl implements IHelloService {
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
//        int a = 1 / 0;
        return String.format("[%s] say: hello %s!",serviceName, name);
    }
}
