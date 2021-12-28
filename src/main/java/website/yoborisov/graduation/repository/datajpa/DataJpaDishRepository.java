package website.yoborisov.graduation.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import website.yoborisov.graduation.model.Dish;
import website.yoborisov.graduation.repository.DishRepository;

import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private final CrudDishRepository crudDishRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository) {
        this.crudDishRepository = crudDishRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish) {
        if (!dish.isNew() && get(dish.id()) == null) {
            return null;
        }
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudDishRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return crudDishRepository.getAll();
    }


}
