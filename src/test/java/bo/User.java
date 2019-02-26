package bo;

public class User {
    private String login;
    private String password;
    private String emailSubject;
    private String emailBody;

    private User(UserBuilder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.emailSubject = builder.emailSubject;
        this.emailBody = builder.emailBody;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSubjectInput() {
        return emailSubject;
    }

    public String getBodyInput() {
        return emailBody;
    }

    public static class UserBuilder {
        private final String login;
        private final String password;
        private String emailSubject;
        private String emailBody;


        public UserBuilder(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public UserBuilder withEmailSubject(String emailSubject) {
            this.emailSubject = emailSubject;
            return this;
        }

        public UserBuilder withEmailBody(String emailBody) {
            this.emailBody = emailBody;
            return this;
        }

        public User build() {
            User user = new User(this);
            return user;
        }
    }
}



