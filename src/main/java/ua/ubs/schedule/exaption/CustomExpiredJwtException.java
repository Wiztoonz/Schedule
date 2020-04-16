package ua.ubs.schedule.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class CustomExpiredJwtException extends RuntimeException {

    public CustomExpiredJwtException(String message) {
        super(message);
    }

}
