package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.dto.ScheduleDto;
import ua.ubs.schedule.entity.Group;
import ua.ubs.schedule.entity.Schedule;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.exaption.ScheduleInformationIncorrectException;
import ua.ubs.schedule.repository.GroupRepository;
import ua.ubs.schedule.repository.ScheduleRepository;
import ua.ubs.schedule.repository.UniversityRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.service.ScheduleService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private UniversityRepository universityRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               UserRepository userRepository,
                               GroupRepository groupRepository,
                               UniversityRepository universityRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.universityRepository = universityRepository;
    }


    @Override
    @Transactional
    public void addSchedule(String teacherName, String groupName, String universityName, Schedule schedule) {
        System.out.println(0);
        User foundedUserByUsername = userRepository.findByUsername(teacherName);
        Group foundedGroupByGroupName = groupRepository.findGroupByName(groupName);
        University foundedUniversityByUniversityName = universityRepository.findUniversityByUniversityName(universityName);
        System.out.println(1);
        if (foundedUserByUsername == null ||
            foundedGroupByGroupName == null ||
            foundedUniversityByUniversityName == null) {
            System.out.println(2);
            throw new ScheduleInformationIncorrectException("Data is not found. Need correct information.");
        }
        System.out.println(3);
        Schedule currentSchedule = scheduleRepository.save(schedule);

        foundedUserByUsername.addSchedule(currentSchedule);
        foundedGroupByGroupName.addSchedule(currentSchedule);
        foundedUniversityByUniversityName.addSchedule(currentSchedule);
    }

    @Override
    @Transactional
    public List<Schedule> findAllByGroup(String groupName, String universityName, LocalDate startDay, LocalDate endDay) {
        return scheduleRepository.findAllByGroupNameAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(groupName, universityName, startDay, currentDate(startDay, endDay));
    }

    @Override
    @Transactional
    public List<Schedule> findAllByLectureRoom(String lectureRoom, String universityName, LocalDate startDay, LocalDate endDay) {
        return scheduleRepository.findAllByLectureRoomAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(lectureRoom, universityName, startDay, currentDate(startDay, endDay));
    }

    @Override
    @Transactional
    public List<Schedule> findAllByTeacher(String teacherName, String teacherSurname, String teacherPatronymic, String universityName, LocalDate startDay, LocalDate endDay) {
        return scheduleRepository.findAllByUser_NameAndUser_SurnameAndUser_PatronymicAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(teacherName, teacherSurname, teacherPatronymic, universityName, startDay, currentDate(startDay, endDay));
    }

    @Override
    @Transactional
    public List<Schedule> findAllByTypeLecture(String typeLecture, String universityName, LocalDate startDay, LocalDate endDay) {
        return scheduleRepository.findAllByTypeLectureAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(typeLecture, universityName, startDay, currentDate(startDay, endDay));
    }

    @Override
    @Transactional
    public List<Schedule> findAllByUniversity(String universityName, LocalDate startDay, LocalDate endDay) {
        return scheduleRepository.findAllByUniversity_UniversityNameAndDateBetweenOrderByDateAsc(universityName, startDay, currentDate(startDay, endDay));
    }

    @Override
    @Transactional
    public List<Schedule> findAllByTime(LocalTime startTime, LocalTime endTime, String universityName, LocalDate startDay, LocalDate endDay) {
        endTime = (endTime == null) ? startTime : endTime;
        return  scheduleRepository.findAllByStartLectureBetweenAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(startTime, endTime, universityName, startDay, currentDate(startDay, endDay));
    }

    public List<ScheduleDto> convertScheduleToScheduleDto(List<Schedule> schedules) {
        if (schedules.isEmpty()) {
            throw new InformationNotFoundException("Information not found");
        }
        ScheduleDto scheduleDto = new ScheduleDto();
        return scheduleDto.listSchedulesToListSchedulesDto(schedules);
    }

    private LocalDate currentDate(LocalDate startDay, LocalDate endDay) {
        return (endDay == null) ? startDay : endDay;
    }

}
