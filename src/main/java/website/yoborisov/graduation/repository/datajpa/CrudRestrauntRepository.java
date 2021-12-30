package website.yoborisov.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;

import java.util.List;
import java.util.Set;

public interface CrudRestrauntRepository extends JpaRepository<Restraunt, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restraunt r WHERE r.id=?1")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restraunt r")
    List<Restraunt> getAll();

    @EntityGraph(attributePaths = {"menuSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restraunt r WHERE r.id=:id")
    Restraunt getMenus(@Param("id") int id);

}
