package website.yoborisov.graduation.controller.menu;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
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
import website.yoborisov.graduation.util.SecurityUtil;

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
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody String deleteMenu(int id) {
        int userId = SecurityUtil.authUserId();
        //int userId = 0;
        log.info("delete Menu {} for user {}", id, userId);
        menuService.delete(id, userId);
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

    @Operation(summary = "Сохранить в базе новое меню для указанного ресторана")
    @PostMapping(path = "/menu")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Menu createMenu(@RequestBody Set<Dish> dishSet, @RequestParam Integer restrauntId) {
        int userId = SecurityUtil.authUserId();
        //int userId = 100001;
        checkLength(dishSet);
        //log.info("create {} for user {}", menu, userId);
        //checkNew(Menu);
        Menu newMenu = new Menu(dishSet);
        Restraunt restraunt = restrauntService.get(restrauntId);
        newMenu.setRestraunt(restrauntService.get(restrauntId));
        newMenu = menuService.create(newMenu, userId);
        for (Dish dish: dishSet){
            dish.setMenu(newMenu);
            dishService.update(dish);
        }
        Set<Menu> newMenuSet = restraunt.getMenuSet();
        newMenuSet.add(newMenu);
        restraunt.setMenuSet(newMenuSet);
        restrauntService.update(restraunt);
        return newMenu;
    }

    @Operation(summary = "Сохранить в базе новый ресторан с заданным меню")
    @PostMapping(path = "/restraunt/new")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Restraunt createRestraunt(@RequestBody Restraunt restraunt){
        //int userId = 100001;
        int userId = SecurityUtil.authUserId();
        log.info("create restraunt {} by user {}", restraunt.getName(), userId);
        Restraunt restraunt1 = restrauntService.create(restraunt);
        for (Menu menu: restraunt.getMenuSet()){
            checkLength(menu.getDishes());
            menu.setRestraunt(restraunt1);
            Menu menu1 = menuService.create(menu, userId);
            for (Dish dish: menu.getDishes()){
                dish.setMenu(menu1);
                dishService.create(dish);
            }
        };
        return restraunt1;
    }

    @Operation(summary = "Обновить меню с заданным id")
    @PutMapping(value = "/menu/{id}")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Menu updateMenu(@RequestBody Menu menu, int id) {
        //int userId = SecurityUtil.authUserId();
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", menu, userId);
        checkLength(menu.getDishes());
        assureIdConsistent((HasId) menu, id);
        return menuService.update(menu, id);
    }

    @Operation(summary = "Обновить данные ресторана с заданным id")
    @PutMapping(value = "/restraunt/{id}")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Restraunt updateRestraunt(@RequestBody Restraunt restraunt, int menuId){
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", restraunt, userId);
        return restrauntService.update(restraunt, menuId);
    }

    @Operation(summary = "Получить все меню ресторана")
    @GetMapping(value = "/restraunt/getmenus/{id}")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Set<Menu> getRestrauntMenus(@PathVariable(value="id") Integer restrauntId){
        log.info("Get menus for restraunt # {}", restrauntId);
        return restrauntService.getMenus(restrauntId);
    }

    @Operation(summary = "Получить список ресторанов с актуальными меню")
    @GetMapping(value = "/restraunt/actualmenus")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody List<Restraunt> getWithActualMenus(){
        log.info("Get actual menus for restraunts");
        return restrauntService.getWithTodaysActualMenu();
    }

/*    @Operation(summary = "Получить все меню")
    @Override
    public @ResponseBody
    @GetMapping(value = "/menu/allbyuser")
    List<Menu> getAllByUser(@RequestParam int userId) {
        return super.getAllByUser(userId);
    }*/

/*    @Operation(summary = "Проголосовать за меню с заданным id, повышая рейтинг ресторана")
    @PutMapping(value = "/menu/vote/{id}")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Menu voteForMenu(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("Vote for Menu № {}, by user {}", id, userId);
        //assureIdConsistent(Menu, id);
        Restraunt restraunt = menuService.get(id).getRestraunt();
        restraunt.increaseVotes();
        return menuService.vote(id);
    }*/
}
