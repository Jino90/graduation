package website.yoborisov.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import website.yoborisov.graduation.model.Restraunt;

import java.util.List;

public interface CrudRestrauntRepository extends JpaRepository<Restraunt, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restraunt r WHERE r.id=?1")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restraunt r")
    List<Restraunt> getAll();



}
