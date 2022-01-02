package website.yoborisov.graduation.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.repository.RestrauntRepository;
import website.yoborisov.graduation.util.RestrauntsUtil;

import java.util.List;
import java.util.Set;

@Service
public class RestrauntService {

    private final RestrauntRepository repository;

    public RestrauntService(RestrauntRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "restraunt", allEntries = true)
    public Restraunt create(Restraunt restraunt) {
        Assert.notNull(restraunt, "user must not be null");
        Assert.notNull(restraunt.getMenuSet(), "Menu must not be null");
        return repository.save(restraunt);
    }

    @CacheEvict(value = "restraunt", allEntries = true)
    public boolean delete(int id) {
        return repository.delete(id);
    }

    public Restraunt get(int id) {
        return repository.get(id);
    }

    @Cacheable("restraunt")
    public List<Restraunt> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "restraunt", allEntries = true)
    public Restraunt update(Restraunt restraunt, int menuId) {
        Assert.notNull(restraunt, "user must not be null");
        return repository.save(restraunt, menuId);
    }

    @CacheEvict(value = "restraunt", allEntries = true)
    public Restraunt update(Restraunt restraunt) {
        Assert.notNull(restraunt, "user must not be null");
        return repository.save(restraunt);
    }

    public Restraunt vote(int restrauntId) {
        Restraunt restraunt = this.get(restrauntId);
        restraunt.increaseVotes();
/*        Menu menu = restraunt.getMenu();
        menu.increaseVotes();
        return update(restraunt, menu.id());*/
        return update(restraunt);
    }

    public Set<Menu> getMenus(int restrauntId){
        return repository.getMenus(restrauntId).getMenuSet();
    }

    public List<Restraunt> getWithTodaysActualMenu() {
        List<Restraunt> restraunts = getAll();
        return RestrauntsUtil.filterNonActualMenus(restraunts);
    }
}
