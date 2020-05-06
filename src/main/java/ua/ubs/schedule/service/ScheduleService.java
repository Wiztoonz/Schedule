package ua.ubs.schedule.service;

import ua.ubs.schedule.dto.ScheduleDto;
import ua.ubs.schedule.entity.Schedule;
import ua.ubs.schedule.response.ScheduleControlPanel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {

    void addSchedule(String teacherName, String groupName, String universityName, Schedule schedule);

    List<ScheduleDto> convertScheduleToScheduleDto(List<Schedule> schedules);

    List<Schedule> findAllByGroup(String groupName, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByLectureRoom(String lectureRoom, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByTeacher(String teacherName, String teacherSurname, String teacherPatronymic, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByTypeLecture(String typeLecture, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByUniversity(String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByTime(LocalTime startTime, LocalTime endTime, String universityName, LocalDate startDay, LocalDate endDay);

    ScheduleControlPanel getScheduleControlPanel();

    List<Schedule> findSchedules(String groupName, String lectureRoom, String teacherName, String teacherSurname, String teacherPatronymic, String typeLecture, String universityName, LocalDate startDay, LocalDate endDay, LocalTime startTime, LocalTime endTime);
}
