import com.epfox.dao.UserDaoOracleImpl;
import com.epfox.dao.UserDaoSqlserverImpl;
import com.epfox.service.UserService;
import com.epfox.service.UserServiceImpl;
import com.epfox.dao.UserDaoMysqlImpl;

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
}
