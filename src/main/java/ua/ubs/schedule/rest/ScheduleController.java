package ua.ubs.schedule.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ubs.schedule.dto.ScheduleDto;
import ua.ubs.schedule.entity.Schedule;
import ua.ubs.schedule.response.ScheduleControlPanel;
import ua.ubs.schedule.service.ScheduleService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ubs/v1/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add/{teacherName}/{groupName}/{universityName}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public void addSchedule(@PathVariable String teacherName,
                            @PathVariable String groupName,
                            @PathVariable String universityName,
                            @RequestBody Schedule schedule) {
        scheduleService.addSchedule(teacherName, groupName, universityName, schedule);
    }

    @GetMapping("/get/control")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ScheduleControlPanel getScheduleControlPanel() {
        return scheduleService.getScheduleControlPanel();
    }

    @GetMapping("/get")
    public List<ScheduleDto> getSchedule(@RequestParam(defaultValue = "") String groupName,
                                         @RequestParam(defaultValue = "") String lectureRoom,
                                         @RequestParam(defaultValue = "") String teacherName,
                                         @RequestParam(defaultValue = "") String teacherSurname,
                                         @RequestParam(defaultValue = "") String teacherPatronymic,
                                         @RequestParam(defaultValue = "") String typeLecture,
                                         @RequestParam String universityName,
                                         @RequestParam(required = false) @DateTimeFormat(
                                                 pattern = "dd.MM.yyyy",
                                                 iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                         @RequestParam(required = false) @DateTimeFormat(
                                                 pattern = "dd.MM.yyyy",
                                                 iso = DateTimeFormat.ISO.DATE) LocalDate endDay,
                                         @RequestParam(required = false) @DateTimeFormat(
                                                 pattern = "HH:mm",
                                                 iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                         @RequestParam(required = false) @DateTimeFormat(
                                                 pattern = "HH:mm",
                                                 iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        List<Schedule> schedules = scheduleService.findSchedules(
                groupName,
                lectureRoom,
                teacherName, teacherSurname, teacherPatronymic,
                typeLecture,
                universityName,
                startDay, endDay,
                startTime, endTime);
        return scheduleService.convertScheduleToScheduleDto(schedules);
    }

}
