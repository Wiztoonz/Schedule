package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.dto.UserDto;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.jwt.JwtTokenUtil;
import ua.ubs.schedule.repository.RoleRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto getUser(String authorization) {
        String username = jwtTokenUtil.getUsernameFromToken(authorization.substring(7));
        UserDto userDto = new UserDto();
        User user = userRepository.findByUsername(username);
        UserDto userInfo = userDto.userToUserDto(user);
        return userInfo;
    }

    @Override
    public List<UserDto> findUsersByRoleName(String roleName) {
        if (roleName.isEmpty()) {
            throw new InformationNotFoundException("Some information is incorrect!");
        }
        List<Role> roles = roleRepository.findAllByName(roleName);
        if (roles.isEmpty()) {
            throw new InformationNotFoundException("Information not found!");
        }
        List<User> users = new ArrayList<>();
        for (Role role : roles) {
            Set<User> userList = role.getUsers();
            users.addAll(userList);
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            UserDto dto = userDto.userToUserDto(user);
            userDtos.add(dto);
        }
        Collections.sort(userDtos);
        return userDtos;
    }

}
