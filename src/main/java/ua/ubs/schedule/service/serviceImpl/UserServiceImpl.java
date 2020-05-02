package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.auth.ApplicationUser;
import ua.ubs.schedule.dto.RoleDto;
import ua.ubs.schedule.dto.UserDto;
import ua.ubs.schedule.dto.UserRoleDto;
import ua.ubs.schedule.dto.UserScheduleDto;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.jwt.JwtTokenUtil;
import ua.ubs.schedule.repository.RoleRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.security.access.AppAccess;
import ua.ubs.schedule.security.roles.SecurityRole;
import ua.ubs.schedule.service.UserService;

import java.util.*;

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
    public List<UserScheduleDto> findUsersByRoleName(String roleName) {
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
        List<UserScheduleDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserScheduleDto userScheduleDto = new UserScheduleDto();
            UserScheduleDto dto = userScheduleDto.userToUserDto(user);
            userDtos.add(dto);
        }
        Collections.sort(userDtos);
        return userDtos;
    }

    @Override
    public AppAccess appAccess(ApplicationUser applicationUser) {
        AppAccess appAccess = new AppAccess();

        Set<GrantedAuthority> accessForAdmin = new HashSet<>(Collections.singletonList(
                new SimpleGrantedAuthority(SecurityRole.ADMIN.name())
        ));
        Set<GrantedAuthority> accessForManager = new HashSet<>(Collections.singletonList(
                new SimpleGrantedAuthority(SecurityRole.MANAGER.name())
        ));
        Set<GrantedAuthority> accessForUser = new HashSet<>(Collections.singletonList(
                new SimpleGrantedAuthority(SecurityRole.USER.name())
        ));
        Set<GrantedAuthority> accessForTeacher = new HashSet<>(Collections.singletonList(
                new SimpleGrantedAuthority(SecurityRole.TEACHER.name())
        ));

        Set<GrantedAuthority> authorities = applicationUser.getAuthorities();

        boolean adminAccess = authorities.containsAll(accessForAdmin);
        boolean managerAccess = authorities.containsAll(accessForManager);
        boolean teacherAccess = authorities.containsAll(accessForTeacher);
        boolean userAccess = authorities.containsAll(accessForUser);

        if (adminAccess) {
            appAccess.setAdminAccess(true);
        }
        if (managerAccess) {
            appAccess.setManagerAccess(true);
        }
        if (teacherAccess) {
            appAccess.setTeacherAccess(true);
        }
        if (userAccess) {
            appAccess.setUserAccess(true);
        }

        return appAccess;
    }

    @Override
    public List<UserRoleDto> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new InformationNotFoundException("Information not found!");
        }
        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        for (User user : users) {
            Set<RoleDto> roleDtos = new HashSet<>();
            UserRoleDto userRoleDto = new UserRoleDto();

            Set<Role> roles = user.getRoles();

            for (Role role : roles) {
                RoleDto roleDto = new RoleDto();
                roleDtos.add(roleDto.roleToRoleDto(role));
            }

            userRoleDto.setRoles(roleDtos);
            userRoleDto.setName(user.getName());
            userRoleDto.setSurname(user.getSurname());
            userRoleDto.setUsername(user.getUsername());
            userRoleDto.setPatronymic(user.getPatronymic());

            userRoleDtos.add(userRoleDto);
        }
        return userRoleDtos;
    }

}
