import com.mayikt.entity.BookEntity;
import com.mayikt.entity.OrderEntity;
import com.mayikt.entity.UserEntity;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 周宇
 * @create 2021-07-23 11:18
 * SpringBean的创建过程 内部注入方式
 */
public class Test01_BeanCreate_InternalInjection {
    public static void main(String[] args) {
        //反射机制 通过无产构造方法创建UserBean
        createUserBean();
        //通过Set方式注入属性，p标签注入属性
        createBookBean();
        //通过有参构造方式注入属性
        createOrderBean();
    }


    /**
     * 反射机制 通过无产构造方法创建UserBean
     */
    public static void createUserBean(){
        //spring配置bean容器 注入 注解
        //new UserEntity()
        // 1.读取xml配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 2.根据bean的id获取bean对象
        UserEntity userEntity = applicationContext.getBean("userEntity", UserEntity.class);
        System.out.println(userEntity);
        userEntity.addUser();
        System.out.println("=============createUserBean Success=============");
    }

    /**
     * 通过Set方式注入属性，p标签注入属性
     */
    private static void createBookBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("book.xml");
        //xml中创建对象和set方法注入属性
        BookEntity bookEntity1 = applicationContext.getBean("bookEntity1", BookEntity.class);
        System.out.println("bookEntity1:" + bookEntity1);
        //使用p标签注入属性
        BookEntity bookEntity2 = applicationContext.getBean("bookEntity2", BookEntity.class);
        System.out.println("bookEntity2:" + bookEntity2);
        System.out.println("=============createBookBean Success=============");
    }

    /**
     * 通过有参构造方式注入属性
     */
    private static void createOrderBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("order.xml");
        OrderEntity orderEntity = applicationContext.getBean("orderEntity", OrderEntity.class);
        OrderEntity orderEntity2 = applicationContext.getBean("orderEntity2", OrderEntity.class);
        System.out.println(orderEntity);
        System.out.println(orderEntity2);
        System.out.println("=============createOrderBean Success=============");
    }
}
