import com.mayikt.entity.MemberEntity;
import com.mayikt.entity.UserEntity;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-23 21:56
 * Bean的声明周期
 */
public class Test07_BeanLifeCycle {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanlifecycle.xml");
        System.out.println("[第四步]-获取使用到的memberEntity");
        MemberEntity memberEntity = applicationContext.getBean("memberEntity", MemberEntity.class);
        System.out.println(memberEntity);
        // 手动让bean容器销毁
        applicationContext.close();
    }
}
