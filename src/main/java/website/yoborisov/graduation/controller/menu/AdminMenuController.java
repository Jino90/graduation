package website.yoborisov.graduation.controller.menu;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.service.MenuService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping(path = "/admin_menu/api")
public class AdminMenuController extends AbstractMenuController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    @Operation(summary = "Удалить меню с заданным id")
    @DeleteMapping(path = "/{id}")
    public @ResponseBody String delete(int id) {
        //int userId = SecurityUtil.authUserId();
        int userId = 0;
        log.info("delete Menu {} for user {}", id, userId);
        return "ОК";
    }

    @Operation(summary = "Сохранить в базе новое меню")
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Menu create(@RequestBody Menu menu) {
        //int userId = SecurityUtil.authUserId();
        int userId = 0;
        log.info("create {} for user {}", menu, userId);
        //checkNew(Menu);
        return service.create(menu, userId);
    }

    @Operation(summary = "Обновить меню с заданным id")
    @PutMapping(value = "/{id}")
    public @ResponseBody Menu update(@RequestBody Menu menu, int id) {
        //int userId = SecurityUtil.authUserId();
        int userId = 0;
        log.info("update {} for user {}", menu, userId);
        //assureIdConsistent(Menu, id);
        return service.update(menu, userId);
    }
}
