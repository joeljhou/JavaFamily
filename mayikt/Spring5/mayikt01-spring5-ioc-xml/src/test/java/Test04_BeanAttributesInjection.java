import com.mayikt.entity.EmpEntity;
import com.mayikt.entity.UserEntity;
import com.mayikt.service.MemberService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-23 19:26
 * bean的基础属性注入
 */
public class Test04_BeanAttributesInjection {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        //注入外部bean
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        memberService.addMember();
        System.out.println("=============注入属性外部bean Success=============");

        //注入属性内部bean
        EmpEntity entity = applicationContext.getBean("empEntity1", EmpEntity.class);
        System.out.println(entity);
        System.out.println("=============注入属性内部bean Success=============");

        //级联赋值 需要提供get方法
        EmpEntity empEntity = applicationContext.getBean("empEntity2",EmpEntity.class);
        System.out.println(empEntity);
        System.out.println("=============级联赋值 Success=============");
    }
}
