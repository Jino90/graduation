package website.yoborisov.graduation.controller.menu;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.model.User;
import website.yoborisov.graduation.service.MenuService;
import website.yoborisov.graduation.service.UserService;
import website.yoborisov.graduation.util.SecurityUtil;
import website.yoborisov.graduation.util.ValidationUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Controller
@RequestMapping(path = "/user_menu/api")
public class UserMenuController extends AbstractMenuController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Проголосовать за меню с заданным id, повышая рейтинг ресторана")
    @PutMapping(value = "/menu/vote/{id}")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody
    Menu voteForMenu(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("Vote for Menu № {}, by user {}", id, userId);
        Menu menu = menuService.get(id);
        User user = userService.get(userId);
        LocalDateTime votingTime = LocalDateTime.now();
        ValidationUtil.checkVoteTime(user.getLastVote());
        menu.increaseVotes();
        if (user.getLastVote() != null &&
                user.getLastVote().toInstant(ZoneOffset.of("+03:00")).atZone(ZoneId.of("Europe/Moscow")).toLocalDate().equals(
                votingTime.toInstant(ZoneOffset.of("+03:00")).atZone(ZoneId.of("Europe/Moscow")
        ).toLocalDate())){
            log.info("Decrease votes for Menu # {}, by user {}", id, userId);
            Menu previousVoted = menuService.get(user.getVotedMenu());
            if (previousVoted.equals(menu)){return previousVoted;}
            else{
            previousVoted.decreaseVotes();
            menuService.update(previousVoted, userId);}
        }
        user.setLastVote(votingTime);
        user.setVotedMenu(menu.getId());
        userService.update(user);
        log.info("Current user: {}", user);
        return menuService.update(menu, userId);
    }
}
