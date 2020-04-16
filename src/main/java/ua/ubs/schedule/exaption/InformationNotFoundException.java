package ua.ubs.schedule.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InformationNotFoundException extends RuntimeException {

    public InformationNotFoundException(String exception) {
        super(exception);
    }

}
