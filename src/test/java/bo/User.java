package bo;

import static utils.RandomString.getRandomString;

public class User {
    private static final String LOGIN = "HannaTest34@gmail.com";
    private static final String PASSWORD = "PasswordPassword";
    public static final String EMAIL_SUBJECT = getRandomString(10);
    public static final String EMAIL_BODY = "Hello, World!";


    public static String getLogin() {
        return LOGIN;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String setSubjectInput() {
        return EMAIL_SUBJECT;
    }

    public static String setBodyInput() {
        return EMAIL_BODY;
    }
}

