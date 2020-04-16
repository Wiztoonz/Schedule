package ua.ubs.schedule.service.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.repository.RoleRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.service.PermitService;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PermitServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Test
    public void setUserPermitShouldSetPermitForSomeUser() {
        PermitService permitService = new PermitServiceImpl(userRepository, roleRepository);
        String username = "Jack";
        Role role = mock(Role.class);
        User user = mock(User.class);
        when(role.getName()).thenReturn("USER");
        when(userRepository.findByUsername(username)).thenReturn(user);

        permitService.setUserPermit(username, role);
        ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepository).findByUsername(usernameArgumentCaptor.capture());
        verify(role).getName();
        when(roleRepository.findByName(role.getName())).thenReturn(role);
        Role foundedRole = verify(roleRepository).findByName(role.getName());
        verify(user).addRole(foundedRole);
    }

    @Test
    public void deleteUserPermitShouldDeletePermitForSomeUser() {
        Role role = mock(Role.class);
        String username = "username";

        PermitService permitService = new PermitServiceImpl(userRepository, roleRepository);
        User user = mock(User.class);

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(role.getName()).thenReturn("roleName");
        when(roleRepository.findByName(role.getName())).thenReturn(role);

        permitService.deleteUserPermit(username, role);

        ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).findByUsername(usernameArgumentCaptor.capture());
        verify(roleRepository).findByName(role.getName());

        verify(user).removeRole(role);
    }
}