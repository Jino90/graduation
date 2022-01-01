package website.yoborisov.graduation.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Изменение голоса после 11:00 запрещено")
public class VoteChangeDepricatedException extends ValidationException {
}
