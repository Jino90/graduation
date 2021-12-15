package website.yoborisov.graduation.repository;

import website.yoborisov.graduation.model.Menu;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu);

    boolean delete(int id, int userId);

    Menu get(int id);

    List<Menu> getAll(int userId);
}
