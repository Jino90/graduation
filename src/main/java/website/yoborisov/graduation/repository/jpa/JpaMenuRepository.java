package website.yoborisov.graduation.repository.jpa;

import org.springframework.transaction.annotation.Transactional;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.User;
import website.yoborisov.graduation.repository.MenuRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMenuRepository implements MenuRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else if (get(menu.id()) == null) {
            return null;
        }
        return em.merge(menu);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public List<Menu> getAll(int userId) {
        return em.createNamedQuery(Menu.ALL_SORTED, Menu.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
