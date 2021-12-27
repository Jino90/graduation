package website.yoborisov.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=?1 and m.author=?2")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Menu m WHERE m.author.id=?1")
    List<Menu> getAllByUser(@Param("userId") int userId);

    @Query("SELECT m FROM Menu m")
    List<Menu> getAll();

    @Query("SELECT m FROM Menu m JOIN FETCH m.author WHERE m.id = ?1 and m.author.id = ?2")
    Menu getWithUser(int id, int userId);

    @Query("SELECT m FROM Menu m JOIN FETCH m.author WHERE m.id = ?1")
    Menu getAuthor(int id);
}
