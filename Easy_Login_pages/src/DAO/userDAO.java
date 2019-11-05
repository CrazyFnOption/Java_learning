package DAO;


import domain.User;
import org.junit.Test;
import util.jdbcUtils;

import javax.sql.DataSource;
import java.sql.SQLException;


public class userDAO extends BaseDAO<User> {

    private DataSource ds = jdbcUtils.getDataSource();

    public User login(User loginUser) throws SQLException {
        String sql = "select * from User where username = ? and password = ?";
        User user = getInstance(ds.getConnection(), sql, loginUser.getUsername(), loginUser.getPassword());
        if (user != null) return user;
        return null;
    }

    public void registered(User user) throws SQLException {
        String sql = "insert into User (username,password) value (?,?) ";
        update(ds.getConnection(),sql,user.getUsername(),user.getPassword());
    }


}
