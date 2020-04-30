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

    @GetMapping("/get/byGroupName")
    public List<ScheduleDto> getAllLectureByGroupNameAndUniversityName(@RequestParam String groupName,
                                                                       @RequestParam String universityName,
                                                 @RequestParam @DateTimeFormat(
                                                         pattern = "dd.MM.yyyy",
                                                         iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                 @RequestParam(required = false) @DateTimeFormat(
                                                         pattern = "dd.MM.yyyy",
                                                         iso = DateTimeFormat.ISO.DATE) LocalDate endDay) {
        List<Schedule> schedules = scheduleService.findAllByGroup(groupName, universityName, startDay, endDay);
        return scheduleService.convertScheduleToScheduleDto(schedules);
    }

    @GetMapping("/get/byLectureRoom")
    public List<ScheduleDto> getAllLectureByLectureRoomAndUniversityName(@RequestParam String lectureRoom,
                                                                         @RequestParam String universityName,
                                                      @RequestParam @DateTimeFormat(
                                                              pattern = "dd.MM.yyyy",
                                                              iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                      @RequestParam(required = false) @DateTimeFormat(
                                                              pattern = "dd.MM.yyyy",
                                                              iso = DateTimeFormat.ISO.DATE) LocalDate endDay) {
        List<Schedule> schedules = scheduleService.findAllByLectureRoom(lectureRoom, universityName, startDay, endDay);
        return scheduleService.convertScheduleToScheduleDto(schedules);
    }

    @GetMapping("/get/byTeacher")
    public List<ScheduleDto> getAllLectureByTeacherAndUniversityName(@RequestParam String teacherName,
                                                                     @RequestParam String teacherSurname,
                                                                     @RequestParam String teacherPatronymic,
                                                                     @RequestParam String universityName,
                                                    @RequestParam @DateTimeFormat(
                                                            pattern = "dd.MM.yyyy",
                                                            iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                    @RequestParam(required = false) @DateTimeFormat(
                                                            pattern = "dd.MM.yyyy",
                                                            iso = DateTimeFormat.ISO.DATE) LocalDate endDay) {
        List<Schedule> schedules = scheduleService.findAllByTeacher(
                teacherName, teacherSurname, teacherPatronymic, universityName, startDay, endDay);
        return scheduleService.convertScheduleToScheduleDto(schedules);
    }

    @GetMapping("/get/byTypeLecture")
    public List<ScheduleDto> getAllLectureByTypeLectureAndUniversityName(@RequestParam String typeLecture,
                                                                         @RequestParam String universityName,
                                                        @RequestParam @DateTimeFormat(
                                                                pattern = "dd.MM.yyyy",
                                                                iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                        @RequestParam(required = false) @DateTimeFormat(
                                                                pattern = "dd.MM.yyyy",
                                                                iso = DateTimeFormat.ISO.DATE) LocalDate endDay) {
        List<Schedule> schedules = scheduleService.findAllByTypeLecture(typeLecture, universityName, startDay, endDay);
        return scheduleService.convertScheduleToScheduleDto(schedules);
    }

    @GetMapping("/get/byUniversity")
    public List<ScheduleDto> getAllLectureByUniversityName(@RequestParam String universityName,
                                          @RequestParam @DateTimeFormat(
                                                 pattern = "dd.MM.yyyy",
                                                 iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                          @RequestParam(required = false) @DateTimeFormat(
                                                 pattern = "dd.MM.yyyy",
                                                 iso = DateTimeFormat.ISO.DATE) LocalDate endDay) {
        List<Schedule> schedules = scheduleService.findAllByUniversity(universityName, startDay, endDay);
        return scheduleService.convertScheduleToScheduleDto(schedules);
    }

    @GetMapping("/get/byTime")
    public List<ScheduleDto> getAllLectureByTimeAndUniversityName(@RequestParam String universityName,
                                                           @RequestParam @DateTimeFormat(
                                                                   pattern = "dd.MM.yyyy",
                                                                   iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                           @RequestParam(required = false) @DateTimeFormat(
                                                                   pattern = "dd.MM.yyyy",
                                                                   iso = DateTimeFormat.ISO.DATE) LocalDate endDay,
                                                           @RequestParam @DateTimeFormat(
                                                                   pattern = "HH:mm",
                                                                   iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
                                                           @RequestParam(required = false) @DateTimeFormat(
                                                                   pattern = "HH:mm",
                                                                   iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
        List<Schedule> schedules = scheduleService.findAllByTime(startTime, endTime, universityName, startDay, endDay);
        return scheduleService.convertScheduleToScheduleDto(schedules);
    }

}
