package website.yoborisov.graduation.util;

import com.sun.jdi.VMOutOfMemoryException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import website.yoborisov.graduation.HasId;
import website.yoborisov.graduation.util.exception.MenuLengthValidationException;
import website.yoborisov.graduation.util.exception.NotFoundException;
import website.yoborisov.graduation.util.exception.VoteChangeDepricatedException;

import javax.validation.*;
import java.time.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ValidationUtil {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ValidationUtil() {
    }

    public static <T> void validate(T bean) {
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    public static <T> void checkLength(T bean) {
        if (bean instanceof Set || bean instanceof List){
            if (((Collection<?>) bean).size() < 2 || ((Collection<?>) bean).size() > 5){
                throw new MenuLengthValidationException();
            }
        }
    }

    public static void checkVoteTime(LocalDateTime localDateTime){
        if (localDateTime == null){return;}
        LocalDateTime now = LocalDateTime.now();
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime elevenHour = todayMidnight.plusHours(11);
        // Запрет изменения голоса после 11 часов в тот же день
        if (todayMidnight.toEpochSecond(ZoneOffset.of("+03:00")) < localDateTime.toEpochSecond(ZoneOffset.of("+03:00"))
                && now.toEpochSecond(ZoneOffset.of("+03:00")) > elevenHour.toEpochSecond(ZoneOffset.of("+03:00")) ){
            throw new VoteChangeDepricatedException();
        }
    }

}
