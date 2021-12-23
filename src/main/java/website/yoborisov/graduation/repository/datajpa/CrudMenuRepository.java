package website.yoborisov.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=?1 and m.author=?2")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Menu m")
    List<Menu> getAll(@Param("userId") int userId);

    @Query("SELECT m FROM Menu m JOIN FETCH m.author WHERE m.id = ?1 and m.author.id = ?2")
    Menu getWithUser(int id, int userId);
    //@Query("SELECT m from Menu m WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    //List<Menu> getBetweenHalfOpen(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT m FROM Menu m JOIN FETCH m.author WHERE m.id = ?1")
    Menu getAuthor(int id);
}
