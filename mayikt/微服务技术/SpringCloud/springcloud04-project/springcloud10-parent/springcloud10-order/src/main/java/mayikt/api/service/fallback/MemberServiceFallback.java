package mayikt.api.service.fallback;

import com.mayikt.api.entity.UserEntity;
import com.mayikt.api.service.IMemberService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.ResponseBase;
import mayikt.api.service.feign.MemberServiceFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 周宇
 * @create 2021-07-21 23:49
 */
@Component
public class MemberServiceFallback extends BaseApiService implements MemberServiceFeign {

    @Override
    public UserEntity getMember(String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("系统错误.没有获取到用户信息");
        return userEntity;
    }

    //服务降级处理友好提示
    @Override
    public ResponseBase getUserInfo() {
        return setResultError("系统错误，请稍后重试！以类的方式服务降级");
    }
}
