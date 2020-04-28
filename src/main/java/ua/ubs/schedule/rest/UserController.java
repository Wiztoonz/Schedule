package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.dto.UserDto;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.security.roles.SecurityRole;
import ua.ubs.schedule.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ubs/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public UserDto getUser(@RequestHeader(value = "Authorization") String authorization) {
        return userService.getUser(authorization);
    }

    @GetMapping("/get/teacher")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public List<UserDto> getUsers(@RequestParam(defaultValue = "TEACHER") String roleName) {
        if (!roleName.equalsIgnoreCase(SecurityRole.TEACHER.name())) {
            throw new InformationNotFoundException("Information not found!");
        }
        return userService.findUsersByRoleName(roleName);
    }

}
