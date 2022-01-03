package website.yoborisov.graduation;

import website.yoborisov.graduation.model.Dish;
import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestrauntTestData {

    public static final MatcherFactory.Matcher<Restraunt> RESTRAUNT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restraunt.class, "registered", "email", "password");

    public static Restraunt getWrongNew(){
        Menu menu = new Menu();
        menu.setDishes(new HashSet<>(List.of(new Dish("dish1", 3, menu))));
        return new Restraunt("New", new HashSet<>(List.of(menu)));
    }

    public static Restraunt getNew(){
        Menu menu = new Menu();
        menu.setId(500);
        Set<Dish> dishSet = new HashSet<>(List.of(new Dish(51,"dish1", 3, menu),
                new Dish(52, "dish2", 2, menu), new Dish(53,"dish3", 4, menu)));
        menu.setDishes(dishSet);
        return new Restraunt("New", new HashSet<>(List.of(menu)));
    }
}
