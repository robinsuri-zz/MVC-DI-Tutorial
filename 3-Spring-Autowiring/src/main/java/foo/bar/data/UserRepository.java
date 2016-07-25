package foo.bar.data;

import foo.bar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by vivek on 7/6/15.
 */
@Repository
public class UserRepository {
    private DataSource dataSource;
    public String getDummyUser() {
        System.out.println("Using dataSource: " + dataSource.toString());
        return "{\"userName\":\"Vivek Bagade\",\"phoneNumber\":7406444421,\"countryCode\":91,\"isRegistered\":false}";
    }

    public void removeUser(User user) {
        System.out.println("Using dataSource: " + dataSource.toString());
        System.out.println("Removing user");
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
