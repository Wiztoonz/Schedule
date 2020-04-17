package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.service.PermitService;

@CrossOrigin
@RestController
@RequestMapping("/ubs/v1/permit")
public class PermitController {

    private final PermitService permitService;

    @Autowired
    public PermitController(PermitService permitService) {
        this.permitService = permitService;
    }

    @PostMapping("/set/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void setUserPermit(@PathVariable String username, @RequestBody Role role) {
        permitService.setUserPermit(username, role);
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUserPermit(@PathVariable String username, @RequestBody Role role) {
        permitService.deleteUserPermit(username, role);
    }


}
