package website.yoborisov.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/admin/api")
public class AdminController {

    @GetMapping(path = "/{string}")
    public @ResponseBody String getvar(@PathVariable String string){
        return "Сам " + string;
    }
}
