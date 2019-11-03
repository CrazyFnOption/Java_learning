package DAO;

import domain.User;
import org.junit.Test;

import java.sql.SQLException;

public class daoTest {
    @Test
    public void test() throws SQLException {
        userDAO dao = new userDAO();
        User user = new User("wangshuxiao","123");
        user.setUsername("wangshuxiao");
        user.setPassword("123");
        System.out.println(dao.login(user));
    }
}
