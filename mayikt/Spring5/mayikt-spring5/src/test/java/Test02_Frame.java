import com.mayikt.entity.UserEntity;
import com.mayikt.factory.UserFactory;
import org.dom4j.DocumentException;

import java.io.IOException;

/**
 * @author 周宇
 * @create 2021-07-23 11:38
 * 反射+工厂手写ioc容器
 */
public class Test02_Frame {

    /**
     * 1.使用解析xml技术 解析spring.xml 配置文件
     * 2.获取 <bean id="" class=""></bean> 类完整路径地址
     * 3.使用反射技术初始化对象
     * 4.使用到工厂封装初始化对象
     */
    public static void main(String[] args) throws DocumentException, IOException {
        UserEntity userEntity = UserFactory.getUserEntity();
        userEntity.addUser();
    }

}
