import com.mayikt.entity.UserEntity;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-23 11:18
 */
public class Test01_BeanCreate {
    public static void main(String[] args) {
        //spring配置bean容器 注入 注解
        //new UserEntity()
        // 1.读取xml配置文件
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 2.根据bean的id获取bean对象
        UserEntity userEntity = classPathXmlApplicationContext.getBean("userEntity", UserEntity.class);
        System.out.println(userEntity);
        userEntity.addUser();
    }
}
