package website.yoborisov.graduation;

import website.yoborisov.graduation.model.User;

import java.io.Serial;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        setTo(user);
    }

    public int getId() {
        return this.user.id();
    }

    public void setTo(User user) {
        user.setPassword(null);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return this.user.toString();
    }
}
