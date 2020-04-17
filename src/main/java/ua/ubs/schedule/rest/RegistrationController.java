package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.service.UserService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/ubs/v1")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public User registration(@RequestBody @Valid User user) {
        userService.registration(user);
        return user;
    }

}
