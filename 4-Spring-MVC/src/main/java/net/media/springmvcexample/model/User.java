package net.media.springmvcexample.model;

/**
 * Created by vivek on 7/6/15.
 */
public class User {
    private String userName;
    private String email;
    private String todo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
