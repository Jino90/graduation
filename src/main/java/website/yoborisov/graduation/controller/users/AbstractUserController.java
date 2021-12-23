package website.yoborisov.graduation.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import website.yoborisov.graduation.model.User;
import website.yoborisov.graduation.service.UserService;

import java.util.List;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        //checkNew(user);
        return service.create(user);
    }

    public boolean delete(int id) {
        log.info("delete {}", id);
        return service.delete(id);
    }

    public User update(User user, int id) {
        log.info("update {} with id={}", user, id);
        //assureIdConsistent(user, id);
        return service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
