package net.media.springmvcexample.repository;

import net.media.springmvcexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by vivek on 7/7/15.
 */
@Repository
public class UserRepository {
    @Autowired private JdbcTemplate jdbcTemplate;

    public void addUser(User user) {
        jdbcTemplate.update("INSERT into user_info(email,userName) VALUES (?,?)", user.getEmail(), user.getUserName());
    }

    public User getUser(String email) {
        return jdbcTemplate.queryForObject("SELECT * from user_info where email=?", User.class, email);
    }
}
