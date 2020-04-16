package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.entity.Faculty;
import ua.ubs.schedule.service.FacultyService;

@RestController
@RequestMapping("/ubs/v1/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/add/{universityName}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void addFaculty(@PathVariable String universityName, @RequestBody Faculty faculty) {
        facultyService.setFaculty(universityName, faculty);
    }

    @DeleteMapping("/delete/{facultyName}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void delete(@PathVariable String facultyName) {
        facultyService.deleteFaculty(facultyName);
    }

}
