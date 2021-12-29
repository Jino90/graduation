package website.yoborisov.graduation.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Меню должно содержать от 2 до 5 блюд")
public class MenuLengthValidationException extends ValidationException {
}
