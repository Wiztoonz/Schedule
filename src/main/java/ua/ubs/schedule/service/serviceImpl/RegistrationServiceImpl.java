package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.repository.RoleRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.security.roles.SecurityRole;
import ua.ubs.schedule.service.RegistrationService;

import javax.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void registration(User user) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Role role = roleRepository.findByName(SecurityRole.USER.name());
            user.addRole(role);
            userRepository.save(user);
    }


}
