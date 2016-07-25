package foo.bar.service;

import com.google.gson.Gson;
import foo.bar.data.UserRepository;
import foo.bar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vivek on 7/6/15.
 */
@Service
public class UserService {
    private UserRepository userRepository;
    private VerificationService verificationService;
    //private Gson gson;Why not?

    public void processDummyUser() {
        String userData = userRepository.getDummyUser();
        User user = parseDbResponse(userData);
        if(!verificationService.verifyUser(user)) {
            userRepository.removeUser(user);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setVerificationService(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    private User parseDbResponse(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, User.class);
    }
}
