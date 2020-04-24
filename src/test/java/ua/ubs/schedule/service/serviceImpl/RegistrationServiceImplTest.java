package ua.ubs.schedule.service.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.repository.RoleRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.service.RegistrationService;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void registrationShouldSaveUser() {
        RegistrationService registrationService = new RegistrationServiceImpl(bCryptPasswordEncoder, roleRepository, userRepository);
        User user = mock(User.class);
        Role role = mock(Role.class);

        when(user.getPassword()).thenReturn("abc123");
        when(roleRepository.findByName("USER")).thenReturn(role);

        registrationService.registration(user);

        String password = user.getPassword();
        verify(bCryptPasswordEncoder).encode(password);
        verify(user).setPassword(bCryptPasswordEncoder.encode(password));
        verify(roleRepository).findByName("USER");
        verify(user).addRole(role);
        verify(userRepository).save(user);
    }

}