package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.exaption.UserIsRegisteredException;
import ua.ubs.schedule.service.RegistrationService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/ubs/v1")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody @Valid User user) {
        try {
            registrationService.registration(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserIsRegisteredException("User has registered.");
        }
        return new ResponseEntity<>(new UserRegisterSuccess("true"), HttpStatus.OK);
    }

    private static class UserRegisterSuccess {

        private String registration;

        public UserRegisterSuccess(String registration) {
            this.registration = registration;
        }

        public String getRegistration() {
            return registration;
        }

    }

}
