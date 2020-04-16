package ua.ubs.schedule.service.serviceImpl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.repository.RoleRepository;
import ua.ubs.schedule.service.RoleService;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void addRoleShouldSaveRole() {
        RoleService roleService = new RoleServiceImpl(roleRepository);

        Role role = mock(Role.class);

        roleService.addRole(role);

        ArgumentCaptor<Role> roleArgumentCaptor = ArgumentCaptor.forClass(Role.class);

        verify(roleRepository).save(roleArgumentCaptor.capture());

        Role value = roleArgumentCaptor.getValue();
        Assert.assertEquals(value, role);
    }

}