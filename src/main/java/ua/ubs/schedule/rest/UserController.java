package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.auth.ApplicationUser;
import ua.ubs.schedule.auth.UserPrincipal;
import ua.ubs.schedule.dto.UserDto;
import ua.ubs.schedule.dto.UserRoleDto;
import ua.ubs.schedule.dto.UserScheduleDto;
import ua.ubs.schedule.security.access.AppAccess;
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
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN', 'TEACHER', 'USER')")
    public UserDto getUser(@RequestHeader(value = "Authorization") String authorization,
                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        ApplicationUser applicationUser = userPrincipal.getApplicationUser();
        AppAccess appAccess = userService.appAccess(applicationUser);
        UserDto user = userService.getUser(authorization);
        user.setAppAccess(appAccess);
        return user;
    }

    @GetMapping("/find")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public List<UserScheduleDto> getUsers(@RequestParam String roleName) {
        return userService.findUsersByRoleName(roleName);
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserRoleDto> getUsersWithRole() {
        return userService.findAll();
    }

}
