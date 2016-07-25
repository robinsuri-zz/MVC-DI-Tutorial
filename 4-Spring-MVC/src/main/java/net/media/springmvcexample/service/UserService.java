package net.media.springmvcexample.service;

import net.media.springmvcexample.helpers.APIPOSTResponse;
import net.media.springmvcexample.model.User;
import net.media.springmvcexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vivek on 7/7/15.
 */
@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public APIPOSTResponse addUser(User user) {
        if(!isValidUser(user)) {
            return new APIPOSTResponse(false, "Is not a valid user");
        }
        try {
            userRepository.addUser(user);
        } catch (Exception e) {
            return new APIPOSTResponse(false, "Couldn't add user at this time");
        }
        return new APIPOSTResponse(true, "");
    }

    private boolean isValidUser(User user) {
        return !user.getUserName().equals("");
    }
}
