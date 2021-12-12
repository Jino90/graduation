package website.yoborisov.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import website.yoborisov.graduation.model.Menu;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

}
