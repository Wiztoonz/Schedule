package ua.ubs.schedule.service;

import ua.ubs.schedule.auth.ApplicationUser;
import ua.ubs.schedule.dto.UserDto;
import ua.ubs.schedule.security.access.AppAccess;

import java.util.List;

public interface UserService {

    UserDto getUser(String authorization);

    List<UserDto> findUsersByRoleName(String roleName);

    AppAccess appAccess(ApplicationUser applicationUser);

}
