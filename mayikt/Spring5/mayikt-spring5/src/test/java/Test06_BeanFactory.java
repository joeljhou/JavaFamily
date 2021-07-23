import com.mayikt.entity.StuEntity;
import com.mayikt.entity.UserEntity;
import com.mayikt.factorybean.MayiktBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-23 21:56
 * bean注入集合类型属性
 */
public class Test06_BeanFactory {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanfactory.xml");
        //普通Bean：在配置文件中定义什么类型与返回的类型需一致；
        //MayiktBean mayiktBean = applicationContext.getBean("mayiktBean", MayiktBean.class);
        //工厂Bean：在配置文件中定义Bean类型与返回类型可以不一致
        // 实现FactoryBean，重写getObject()方法
        UserEntity userEntity = (UserEntity) applicationContext.getBean("mayiktBean");
        System.out.println(userEntity);
    }
}
