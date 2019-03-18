package com.practice.personal.modules.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.practice.personal.modules.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    //断路器配置，当无法调用以下方法时，就会调用自定的miaoshaError方法
    @HystrixCommand(fallbackMethod = "miaoshaError")
    public String miaosha(String goodId) {
        // 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
        return restTemplate.getForObject("http://INVENTORY-SERVICE/inventory/" + goodId,String.class);
    }

    public String miaoshaError(String goodId){
        return "秒杀商品-"+goodId+"失败";
    }

}
