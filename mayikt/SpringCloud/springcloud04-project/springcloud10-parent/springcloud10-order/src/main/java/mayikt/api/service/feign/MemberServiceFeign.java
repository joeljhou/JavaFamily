package mayikt.api.service.feign;

import com.mayikt.api.service.IMemberService;
import mayikt.api.service.fallback.MemberServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 周宇
 * @create 2021-07-21 0:06
 */
@FeignClient(value = "app-mayikt-member",fallback = MemberServiceFallback.class)
public interface MemberServiceFeign extends IMemberService {
    //服务降级，熔断
    //实体类存放在接口项目，还是存放在实现项目 实体类存放在接口项目里面
    //实体类和定义的接口信息存放在接口项目
    //代码实现存放在接口实现类里面

}
