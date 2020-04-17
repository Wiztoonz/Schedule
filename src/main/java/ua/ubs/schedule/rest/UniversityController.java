package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.service.UniversityService;

@CrossOrigin
@RestController
@RequestMapping("/ubs/v1/university")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void addUniversity(@RequestBody University university) {
        universityService.addUniversity(university);
    }

    @PutMapping("/update/{universityName}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void updateUniversity(@PathVariable String universityName, @RequestBody University university) {
        universityService.updateUniversityByName(universityName, university);
    }

}
