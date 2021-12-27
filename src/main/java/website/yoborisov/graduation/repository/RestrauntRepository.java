package website.yoborisov.graduation.repository;

import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;

import java.util.List;

public interface RestrauntRepository {

    Restraunt save(Restraunt restraunt, int menuId);

    boolean delete(int id);

    Restraunt get(int id);

    List<Restraunt> getAll();

}
