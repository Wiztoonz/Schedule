package ua.ubs.schedule.service;

import ua.ubs.schedule.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUser(String authorization);

    List<UserDto> findUsersByRoleName(String roleName);

}
