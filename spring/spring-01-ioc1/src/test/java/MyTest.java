import com.epfox.dao.UserDaoOracleImpl;
import com.epfox.dao.UserDaoSqlserverImpl;
import com.epfox.service.UserService;
import com.epfox.service.UserServiceImpl;
import com.epfox.dao.UserDaoMysqlImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //方法1：写死
//        UserService userService = new UserServiceImpl();
//        userService.getUser();
        //方式2：动态写入
        UserService userService = new UserServiceImpl();
        ((UserServiceImpl)userService).setUserDao(new UserDaoSqlserverImpl());
        userService.getUser();
    }

    @Test
    public void testSpring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");
        userServiceImpl.getUser();

    }

}

