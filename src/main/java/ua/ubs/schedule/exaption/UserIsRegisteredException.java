package ua.ubs.schedule.exaption;

import org.springframework.dao.DataIntegrityViolationException;

public class UserIsRegisteredException extends DataIntegrityViolationException {

    public UserIsRegisteredException(String message) {
        super(message);
    }

}
