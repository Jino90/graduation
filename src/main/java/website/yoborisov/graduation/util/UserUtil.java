package website.yoborisov.graduation.util;

import website.yoborisov.graduation.model.User;

public class UserUtil {
    public static User prepareToSave(User user) {
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
