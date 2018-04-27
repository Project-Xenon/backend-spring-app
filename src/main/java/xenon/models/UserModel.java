package xenon.models;

public class UserModel {
    private final String firstName;
    private final String lastName;
    private final UserStudentStanding standing;

    private UserModel(UserModelBuilder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        standing = builder.standing;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserStudentStanding getStanding() {
        return standing;
    }

    public static class UserModelBuilder {
        private final String firstName;
        private String lastName;
        private UserStudentStanding standing;

        public UserModelBuilder(String firstName) {
            this.firstName = firstName;
        }

        public UserModelBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserModelBuilder standing(UserStudentStanding standing) {
            this.standing = standing;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }
}
