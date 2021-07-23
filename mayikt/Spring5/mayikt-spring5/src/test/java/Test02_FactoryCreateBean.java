import com.mayikt.service.UserService;

/**
 * @author 周宇
 * @create 2021-07-23 12:51
 * 使用工厂模式创建对象
 */
public class Test02_FactoryCreateBean {
    public static void main(String[] args) {
        UserService userService = new UserService(); //调用 UserDaoFactory.getUserDao();
        userService.addUser();
    }
}
