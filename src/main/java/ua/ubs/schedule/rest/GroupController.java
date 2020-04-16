package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.entity.Group;
import ua.ubs.schedule.service.GroupService;

@RestController
@RequestMapping("/ubs/v1/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/add/{universityName}/{facultyName}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void addGroup(@PathVariable String universityName, @PathVariable String facultyName, @RequestBody Group group) {
        groupService.addGroup(universityName, facultyName, group);
    }

}
