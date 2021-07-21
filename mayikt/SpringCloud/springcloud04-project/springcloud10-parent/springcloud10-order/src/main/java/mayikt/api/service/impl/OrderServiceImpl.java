package mayikt.api.service.impl;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.api.service.IOrderService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.ResponseBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import mayikt.api.service.feign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-20 23:38
 */
@RestController
public class OrderServiceImpl extends BaseApiService implements IOrderService {

    //订单服务集成会员服务接口，用来实现feign客户端 减少重复的接口代码
    @Autowired
    private MemberServiceFeign memberServiceFeign;

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
     * 使用Hystrix解决服务雪崩效应
     *
     * @HystrixCommand <br>
     * 默认开启隔离方式 以线程程方式<br>
     * 默认开启服务降级执行方法 orderToMemberUserInfoFallback<br>
     * 默认开启服务熔断机制<br>
     */
    @Override
    @HystrixCommand(fallbackMethod = "orderToMemberUserInfoFallback")
    @RequestMapping("/orderToMemberUserInfoHystrix")
    public ResponseBase orderToMemberUserInfoHystrix() {
        System.out.println("orderToMemberUserInfoHystrix:" + "当前线程池名称：" + Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }

    public ResponseBase orderToMemberUserInfoFallback() {
        return setResultError("返回一个友好的提示，服务降级，服务器慢，请稍后重试！");
    }

}
