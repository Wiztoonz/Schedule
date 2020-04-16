package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ubs/v1/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addRole(@RequestBody @Valid Role role) {
        roleService.addRole(role);
    }

}
