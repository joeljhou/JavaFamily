import com.mayikt.entity.StuEntity;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-23 21:56
 * bean注入集合类型属性
 */
public class Test05_BeanCollectionAttributes {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_collection.xml");
        //注入外部bean
        StuEntity stuEntity = applicationContext.getBean("stuEntity", StuEntity.class);
        System.out.println(stuEntity);
        System.out.println("=============注入属性外部bean Success=============");
    }
}
