package ccsu.edu.club.cs.Xenon.DAOs;

import ccsu.edu.club.cs.Xenon.Models.UserModel;

public interface UserFinder {
    public UserModel searchOneUser(String firstName, String lastName);

    public String addOneUser(UserModel newUser);
}
