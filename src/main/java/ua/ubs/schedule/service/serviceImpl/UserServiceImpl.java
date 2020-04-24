package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.dto.UserDto;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.jwt.JwtTokenUtil;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.security.roles.SecurityRole;
import ua.ubs.schedule.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
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
    public List<UserDto> findUsers(String name, String surname, String patronymic) {
        List<UserDto> usersDto = new ArrayList<>();

        if (name.isEmpty() || surname.isEmpty() || patronymic.isEmpty()) {
            throw new InformationNotFoundException("Some information is incorrect!");
        }
        List<User> users = userRepository.findAllByNameAndSurnameAndPatronymicOrderByName(name, surname, patronymic);
        if (users.isEmpty()) {
            throw new InformationNotFoundException("Information not found!");
        }
        for (User user : users) {
            UserDto userDto = new UserDto();
            for (Role role : user.getRoles()) {
                String roleName = role.getName();
                if (roleName.equals(SecurityRole.TEACHER.name())) {
                    UserDto foundUser = userDto.userToUserDto(user);
                    usersDto.add(foundUser);
                }
            }
        }
        return usersDto;
    }
}
