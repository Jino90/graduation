package website.yoborisov.graduation.controller.menu;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import website.yoborisov.graduation.HasId;
import website.yoborisov.graduation.model.Dish;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.model.User;
import website.yoborisov.graduation.service.DishService;
import website.yoborisov.graduation.service.MenuService;
import website.yoborisov.graduation.service.RestrauntService;
import static website.yoborisov.graduation.util.ValidationUtil.assureIdConsistent;
import static website.yoborisov.graduation.util.ValidationUtil.checkLength;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping(path = "/admin_menu/api")
public class AdminMenuController extends AbstractMenuController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestrauntService restrauntService;

    @Autowired
    private DishService dishService;

    @Operation(summary = "Удалить меню с заданным id")
    @DeleteMapping(path = "/menu/{id}")
    public @ResponseBody String deleteMenu(int id) {
        //int userId = SecurityUtil.authUserId();
        int userId = 0;
        log.info("delete Menu {} for user {}", id, userId);
        return "ОК";
    }

/*    @Operation(summary = "Сохранить в базе новое меню")
    @PostMapping(path = "/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Menu createMenu(@RequestBody Menu menu) {
        //int userId = SecurityUtil.authUserId();
        int userId = 0;
        log.info("create {} for user {}", menu, userId);
        //checkNew(Menu);
        return menuService.create(menu, userId);
    }*/

    @Operation(summary = "Сохранить в базе новое меню")
    @PostMapping(path = "/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Menu createMenu(@RequestBody Set<Dish> dishSet) {
        //int userId = SecurityUtil.authUserId();
        int userId = 100001;
        checkLength(dishSet);
        //log.info("create {} for user {}", menu, userId);
        //checkNew(Menu);
        Menu menu = menuService.create(new Menu(dishSet), userId);
        for (Dish dish: dishSet){
            dish.setMenu(menu);
            dishService.update(dish);
        }
        return menu;
    }

    @Operation(summary = "Сохранить в базе новый ресторан с заданным меню")
    @PostMapping(path = "/restraunt/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Restraunt createRestraunt(@RequestBody Restraunt restraunt, int menuId){
        log.info("create {} for user {}", restraunt, menuId);
        return restrauntService.create(restraunt, menuId);
    }

    @Operation(summary = "Обновить меню с заданным id")
    @PutMapping(value = "/menu/{id}")
    public @ResponseBody Menu updateMenu(@RequestBody Menu menu, int id) {
        //int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", menu, id);
        checkLength(menu.getDishes());
        assureIdConsistent((HasId) menu, id);
        return menuService.update(menu, id);
    }

    @Operation(summary = "Обновить данные ресторана с заданным id")
    @PutMapping(value = "/restraunt/{id}")
    public @ResponseBody Restraunt updateRestraunt(@RequestBody Restraunt restraunt, int menuId){
        log.info("update {} for user {}", restraunt, menuId);
        return restrauntService.update(restraunt, menuId);
    }

    @Operation(summary = "Получить все меню")
    @Override
    public @ResponseBody
    @GetMapping(value = "/menu/allbyuser")
    List<Menu> getAllByUser(@RequestParam int userId) {
        return super.getAllByUser(userId);
    }

    @Operation(summary = "Проголосовать за меню с заданным id")
    @PutMapping(value = "/menu/vote/{id}")
    public @ResponseBody Menu voteForMenu(int id) {
        //int userId = SecurityUtil.authUserId();
        log.info("Vote for Menu № {}", id);
        //assureIdConsistent(Menu, id);
        return menuService.vote(id);
    }

}
