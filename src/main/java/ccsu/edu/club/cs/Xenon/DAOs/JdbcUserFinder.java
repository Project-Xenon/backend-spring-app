package ccsu.edu.club.cs.Xenon.DAOs;

import ccsu.edu.club.cs.Xenon.Models.UserModel;
import ccsu.edu.club.cs.Xenon.Models.UserStudentStanding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserFinder implements UserFinder {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserModel searchOneUser(String firstName, String lastName) {
        UserModel testUser = new UserModel.UserModelBuilder("Deepankar")
                .lastName("Malhan")
                .standing(UserStudentStanding.SENIOR)
                .build();

        if (testUser.getFirstName().equalsIgnoreCase(firstName) && testUser.getLastName().equalsIgnoreCase(lastName)) {
            return testUser;
        }
        return null;
    }

    @Override
    public String addOneUser(UserModel newUser) {
        return "Added a new user: " + newUser.toString();
    }
}
