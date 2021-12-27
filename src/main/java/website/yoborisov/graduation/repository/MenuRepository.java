package website.yoborisov.graduation.repository;

import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu, int userId);

    boolean delete(int id, int userId);

    Menu get(int id);

    List<Menu> getAllByUser(int userId);

    List<Menu> getAll();

    default Menu getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
