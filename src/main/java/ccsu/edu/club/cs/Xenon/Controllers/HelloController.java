package ccsu.edu.club.cs.Xenon.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ccsu.edu.club.cs.Xenon.Models.UserModel;
import ccsu.edu.club.cs.Xenon.Models.UserStudentStanding;

@RestController
@RequestMapping("/api/test")
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Gson GSON_OBJ = new Gson();

    @RequestMapping(value="/testUser", method=RequestMethod.GET, produces ="application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String testUser() {

        UserModel newUser = new UserModel.UserModelBuilder("Deepankar")
                .lastName("Malhan")
                .standing(UserStudentStanding.SENIOR)
                .build();

        return GSON_OBJ.toJson(newUser);
    }

    @RequestMapping(value="/newUser", method=RequestMethod.POST, produces ="application/json; charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String newUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("standing") String standing
    ) {
        UserStudentStanding tempStanding = null;

        switch (standing) {
            case "SENIOR":
                tempStanding = UserStudentStanding.SENIOR;
                break;
            case "JUNIOR":
                tempStanding = UserStudentStanding.JUNIOR;
                break;
        }

        UserModel newUser = new UserModel.UserModelBuilder(firstName)
                .lastName(lastName)
                .standing(tempStanding)
                .build();

        return GSON_OBJ.toJson(newUser);
    }
}