package foo.bar.service;

import foo.bar.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by vivek on 7/6/15.
 */
@Service
public class VerificationService {
    public boolean verifyUser(User user) {
        return user.isRegistered();
    }
}
