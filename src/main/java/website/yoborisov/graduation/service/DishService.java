package website.yoborisov.graduation.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import website.yoborisov.graduation.model.Dish;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.repository.DishRepository;
import website.yoborisov.graduation.repository.RestrauntRepository;

import java.util.List;

@Service
public class DishService {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "dish", allEntries = true)
    public Dish create(Dish dish) {
        Assert.notNull(dish, "user must not be null");
        return repository.save(dish);
    }

    @CacheEvict(value = "dish", allEntries = true)
    public boolean delete(int id) {
        return repository.delete(id);
    }

    public Dish get(int id) {
        return repository.get(id);
    }

    @Cacheable("dish")
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "dish", allEntries = true)
    public Dish update(Dish dish) {
        Assert.notNull(dish, "user must not be null");
        return repository.save(dish);
    }
}
