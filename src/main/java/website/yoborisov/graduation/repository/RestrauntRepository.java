package website.yoborisov.graduation.repository;

import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;

import java.util.List;

public interface RestrauntRepository {

    Restraunt save(Restraunt restraunt, int menuId);

    Restraunt save(Restraunt restraunt);

    boolean delete(int id);

    Restraunt get(int id);

    List<Restraunt> getAll();

    Restraunt setMenu(Restraunt restraunt, int menuId);

    default Restraunt getMenus(Integer id){
        throw new UnsupportedOperationException();
    }

}
