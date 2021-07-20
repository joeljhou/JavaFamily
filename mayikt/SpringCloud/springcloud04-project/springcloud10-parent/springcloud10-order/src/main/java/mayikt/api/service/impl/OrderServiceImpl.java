package mayikt.api.service.impl;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.api.service.IOrderService;
import mayikt.api.service.feign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周宇
 * @create 2021-07-20 23:38
 */
@RestController
public class OrderServiceImpl implements IOrderService {

    //订单服务集成会员服务接口，用来实现feign客户端 减少重复的接口代码
    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @Override
    @RequestMapping("/getOrderToMember")
    public String getOrderToMember(String name) {
        UserEntity user = memberServiceFeign.getMember(name);
        return user == null ? "没有找到用户信息" : user.toString();
    }

}
