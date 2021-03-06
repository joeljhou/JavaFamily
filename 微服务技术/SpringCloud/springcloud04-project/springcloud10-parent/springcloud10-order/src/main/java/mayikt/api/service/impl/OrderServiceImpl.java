package mayikt.api.service.impl;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.api.service.IOrderService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.ResponseBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import mayikt.api.service.feign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-20 23:38
 */
@RestController
public class OrderServiceImpl extends BaseApiService implements IOrderService {

    @Value("${server.port}")
    private String serverPort;

    //订单服务集成会员服务接口，用来实现feign客户端 减少重复的接口代码
    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @RequestMapping("/")
    public String index() {
        return "我是订单服务项目，serverPort：" + serverPort;
    }

    @Override
    @RequestMapping("/orderToMember")
    public ResponseBase orderToMember(String name) {
        System.out.println("name:" + name);
        UserEntity user = memberServiceFeign.getMember(name);
        if (user == null) {
            return setResultError("没有查询到用户信息");
        }
        return setResultSuccess(user);
    }

    //订单服务接口
    @Override
    @RequestMapping("/getOrderInfo")
    public ResponseBase getOrderInfo() {
        System.out.println("orderToUserInfo:" + "当前线程池名称：" + Thread.currentThread().getName());
        return setResultSuccess("getOrderInfo");
    }

    //没有解决服务雪崩效应
    @Override
    @RequestMapping("/orderToMemberUserInfo")
    public ResponseBase orderToMemberUserInfo() {
        System.out.println("orderToMemberUserInfo:" + "当前线程池名称：" + Thread.currentThread().getName());
        //延迟1.5s
        return memberServiceFeign.getUserInfo();
    }

    /**
     * <p>使用Hystrix解决服务雪崩效应 第一种写法 使用注解方式</p> <br>
     * @HystrixCommand <br>
     * 默认开启隔离方式 以线程程方式<br>
     * 默认开启服务降级执行方法 orderToMemberUserInfoFallback<br>
     * 默认开启服务熔断机制<br>
     */
    @Override
    //@HystrixCommand(fallbackMethod = "orderToMemberUserInfoFallback")
    @RequestMapping("/orderToMemberUserInfoHystrix")
    public ResponseBase orderToMemberUserInfoHystrix() {
        System.out.println("orderToMemberUserInfoHystrix:" + "当前线程池名称：" + Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }

    //Hystrix 第二种写法 使用类方式
    @RequestMapping("/orderToMemberUserInfoHystrix_demo02")
    public ResponseBase orderToMemberUserInfoHystrixDemo02() {
        System.out.println("orderToMemberUserInfoHystrix_Demo02:" + "当前线程池名称：" + Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }

    public ResponseBase orderToMemberUserInfoFallback() {
        return setResultError("返回一个友好的提示，服务降级，服务器慢，请稍后重试！");
    }

    //Hystrix 有两种方式配置保护服务 通过注解和接口形式

}
