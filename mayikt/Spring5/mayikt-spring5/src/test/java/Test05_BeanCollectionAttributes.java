import com.mayikt.entity.StuEntity;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-23 21:56
 * bean注入集合类型属性
 */
public class Test05_BeanCollectionAttributes {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("collection.xml");
        //注入外部bean
        StuEntity stuEntity = applicationContext.getBean("stuEntity", StuEntity.class);
        System.out.println(stuEntity);
        //对list公共部分进行提取
        StuEntity stuEntity2 = applicationContext.getBean("stuEntity2", StuEntity.class);
        System.out.println(stuEntity2);
        System.out.println("=============注入属性外部bean Success=============");
    }
}
