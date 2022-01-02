package website.yoborisov.graduation.controller.users;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.yoborisov.graduation.View;
import website.yoborisov.graduation.model.User;

import java.util.List;

@Controller
@RequestMapping(path = "/users/api")
public class UsersController extends AbstractUserController {

    @Operation(summary = "Получить список всех пользователей")
    @Override
    public @ResponseBody
    @GetMapping(value = "/all")
    List<User> getAll() {
        return super.getAll();
    }

    @Operation(summary = "Получить пользователя с заданным id")
    @Override
    @GetMapping(value = "/user")
    public @ResponseBody User get(int id) {
        return super.get(id);
    }

    @Operation(summary = "Создать пользователя с заданным id")
    @Override
    @PostMapping(path = "/user")
    public @ResponseBody User create(@Validated(View.Web.class) @RequestBody User user) {
        return super.create(user);
    }

    @Operation(summary = "Удалить пользователя с заданным id")
    @Override
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void delete(int id) {
        super.delete(id);
    }

    @Operation(summary = "Обновить данные по пользователю с заданным id")
    @Override
    @PutMapping(value = "/{id}")
    public @ResponseBody User update(User user, int id) {
        return super.update(user, id);
    }

    @Operation(summary = "Получить пользователя с заданным e-mail")
    @Override
    @GetMapping(value = "/userbyemail")
    public @ResponseBody User getByMail(@RequestParam String email) {
        return super.getByMail(email);
    }
}
