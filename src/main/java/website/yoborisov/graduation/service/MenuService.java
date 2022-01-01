package website.yoborisov.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.User;
import website.yoborisov.graduation.repository.MenuRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu get(int id) {
        return repository.get(id);
    }

    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    public List<Menu> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }

    public List<Menu> getAll() {
        return repository.getAll();
    }

    public Menu update(Menu Menu, int userId) {
        Assert.notNull(Menu, "Menu must not be null");
        return repository.save(Menu, userId);
    }

    public Menu create(Menu Menu, int userId) {
        Assert.notNull(Menu, "Menu must not be null");
        Menu.setPublishDate(LocalDateTime.now());
        return repository.save(Menu, userId);
    }

    public Menu vote(int menuId, int userId) {
        Menu menu = this.get(menuId);
        menu.increaseVotes();
        return update(menu, userId);
    }

    public Menu getWithUser(int id, int userId) {
        return repository.getWithUser(id, userId);
    }

}