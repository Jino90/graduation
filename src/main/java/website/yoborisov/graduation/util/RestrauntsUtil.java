package website.yoborisov.graduation.util;

import website.yoborisov.graduation.model.Menu;
import website.yoborisov.graduation.model.Restraunt;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class RestrauntsUtil {

    public RestrauntsUtil(){}

    public static List<Restraunt> filterNonActualMenus(List<Restraunt> restraunts){
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        List<Restraunt> filtered = new ArrayList<>();
        for (Restraunt restraunt: restraunts) {
            Menu latestMenu = restraunt.getMenuSet().stream().max(Comparator.comparing(Menu::getPublishDate)).
                    orElse(null);
            if (latestMenu != null && todayMidnight.toEpochSecond(ZoneOffset.UTC) <= latestMenu.getPublishDate().toEpochSecond(
                    ZoneOffset.UTC)){
                Set<Menu> menus = new HashSet<>();
                menus.add(latestMenu);
                restraunt.setMenuSet(menus);
                filtered.add(restraunt);
        }
        }
        return filtered;
    }

    public static Restraunt getTopRanked(List<Restraunt> restraunts){
        return restraunts.stream().max(Comparator.comparing(restraunt -> restraunt.getLastMenu().getVotes())).orElse(
                null);
    }
}
