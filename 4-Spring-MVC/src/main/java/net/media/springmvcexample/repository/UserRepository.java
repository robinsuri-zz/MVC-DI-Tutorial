package net.media.springmvcexample.repository;

import net.media.springmvcexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vivek on 7/7/15.
 */
@Repository
public class UserRepository {
    @Autowired private JdbcTemplate jdbcTemplate;

    public int addUser(User user) {
        jdbcTemplate.update("INSERT into user_info(email,userName,todo) VALUES (?,?,?)", user.getEmail(), user.getUserName(), user.getTodo());
        int id  = jdbcTemplate.queryForObject("SELECT id from user_info where email=?", Integer.class, user.getEmail());
        return id;
    }


    public User getUser(int id) {
        return jdbcTemplate.queryForObject("SELECT userName, email, todo from user_info where id=?",new Object[]{id}, new TodoMaper());
    }

    private static final class TodoMaper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setTodo(rs.getString("todo"));
            user.setUserName(rs.getString("userName"));
            return user;
        }
    }
}
