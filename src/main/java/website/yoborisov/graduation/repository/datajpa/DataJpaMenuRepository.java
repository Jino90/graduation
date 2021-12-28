package website.yoborisov.graduation.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.repository.MenuRepository;
import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {

    private final CrudMenuRepository crudMenuRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudUserRepository crudUserRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int userId) {
        if (!menu.isNew() && get(menu.id()) == null) {
            return null;
        }
        menu.setAuthor(crudUserRepository.getWithMenu(userId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMenuRepository.delete(id, userId) != 0;
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Menu> getAllByUser(int userId) {
        return crudMenuRepository.getAllByUser(userId);
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.getAll();
    }

    @Override
    public Menu getWithUser(int id, int userId) {
        return crudMenuRepository.getWithUser(id, userId);
    }



}
