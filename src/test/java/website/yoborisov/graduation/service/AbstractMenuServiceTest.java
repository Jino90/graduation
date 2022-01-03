package website.yoborisov.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import website.yoborisov.graduation.controller.menu.AdminMenuController;
import website.yoborisov.graduation.controller.menu.UserMenuController;
import website.yoborisov.graduation.model.Dish;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.util.exception.MenuLengthValidationException;
import website.yoborisov.graduation.util.exception.VoteChangeDepricatedException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;
import static website.yoborisov.graduation.RestrauntTestData.*;

public class AbstractMenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuService menuService;

    @Autowired
    protected RestrauntService restrauntService;

    @Autowired
    protected DishService dishService;

    @Autowired
    protected UserMenuController userMenuController;

    @Autowired
    protected AdminMenuController adminMenuController;

    @Test
    void createRestrauntWithException() throws Exception{
        assertThrows(MenuLengthValidationException.class, () -> restrauntService.create(getWrongNew()));
    }

    @Test
    @WithUserDetails(value="admin@gmail.com")
    void voteChageDeprication() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.ofHours(+3));
        System.out.println(now.getHour());
        if (now.getHour() >= 11){
            userMenuController.voteForMenu(1);
            assertThrows(VoteChangeDepricatedException.class, () -> userMenuController.voteForMenu(1));
        }
        else{
            userMenuController.voteForMenu(1);
            userMenuController.voteForMenu(1);
            assertEquals(1, restrauntService.get(1).getLastMenu().getVotes());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
