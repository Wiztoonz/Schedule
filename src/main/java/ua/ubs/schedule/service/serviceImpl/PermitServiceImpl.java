package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.repository.RoleRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.service.PermitService;

import javax.transaction.Transactional;

@Service
public class PermitServiceImpl implements PermitService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PermitServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void setUserPermit(String username, Role role) {
        User user = userRepository.findByUsername(username);
        Role roleName = roleRepository.findByName(role.getName());
        user.addRole(roleName);
    }

    @Override
    @Transactional
    public void deleteUserPermit(String username, Role role) {
        User foundUserByName = userRepository.findByUsername(username);
        Role foundRoleByName = roleRepository.findByName(role.getName());
        foundUserByName.removeRole(foundRoleByName);
    }

}
