package website.yoborisov.graduation.controller.menu;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.service.MenuService;
import website.yoborisov.graduation.service.RestrauntService;
import website.yoborisov.graduation.util.SecurityUtil;

import java.util.List;

public abstract class AbstractMenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    @Autowired
    private RestrauntService restrauntService;

    @Operation(summary = "Получить меню с заданным id")
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Menu get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get Menu {} for user {}", id, userId);
        return service.get(id);
    }

/*    @Operation(summary = "Получить все меню, созданные пользователем")
    @GetMapping(path = "/allbyuser")
    public @ResponseBody
    List<Menu> getAllByUser(@RequestParam(required = false) int userId) {
        //int userId = SecurityUtil.authUserId();
        log.info("get Menu list for user {}", userId);
        return service.getAllByUser(userId);
    }*/

/*    @Operation(summary = "Получить все меню")
    @GetMapping(path = "/all")
    public @ResponseBody
    List<Menu> getAll() {
        //int userId = SecurityUtil.authUserId();
        return service.getAll();
    }*/

    @Operation(summary = "Получить все актуальные для ресторанов меню")
    @GetMapping(path = "/alltodaymenus")
    public @ResponseBody List<Restraunt> getAllTodayMenus() {
        int userId = SecurityUtil.authUserId();
        log.info("get alltodaymenus for user {}", userId);
        return restrauntService.getWithTodaysActualMenu();
    }
}
