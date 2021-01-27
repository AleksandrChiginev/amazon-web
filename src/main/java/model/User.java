package model;

public class User {
    private String userMail;
    private String password;

    public User(String userMail, String password) {
        this.userMail = userMail;
        this.password = password;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
