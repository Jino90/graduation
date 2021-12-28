package website.yoborisov.graduation.repository;

import website.yoborisov.graduation.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll();
}
