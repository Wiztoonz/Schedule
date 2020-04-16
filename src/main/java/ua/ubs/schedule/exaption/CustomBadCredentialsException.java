package ua.ubs.schedule.exaption;

import org.springframework.security.authentication.BadCredentialsException;

public class CustomBadCredentialsException extends BadCredentialsException {

    public CustomBadCredentialsException(String msg) {
        super(msg);
    }

}
