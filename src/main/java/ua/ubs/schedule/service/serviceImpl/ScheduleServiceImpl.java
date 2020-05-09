package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.dto.*;
import ua.ubs.schedule.entity.*;
import ua.ubs.schedule.exaption.CustomInformationNotFoundException;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.exaption.ScheduleInformationIncorrectException;
import ua.ubs.schedule.repository.GroupRepository;
import ua.ubs.schedule.repository.ScheduleRepository;
import ua.ubs.schedule.repository.UniversityRepository;
import ua.ubs.schedule.repository.UserRepository;
import ua.ubs.schedule.response.ScheduleControlPanel;
import ua.ubs.schedule.security.roles.SecurityRole;
import ua.ubs.schedule.service.ScheduleService;
import ua.ubs.schedule.service.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private UniversityRepository universityRepository;

    private UserService userService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               UserRepository userRepository,
                               GroupRepository groupRepository,
                               UniversityRepository universityRepository,
                               UserService userService) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.universityRepository = universityRepository;
        this.userService = userService;
    }


    @Override
    @Transactional
    public void addSchedule(String teacherName, String groupName, String universityName, Schedule schedule) {
        User foundedUserByUsername = userRepository.findByUsername(teacherName);
        Group foundedGroupByGroupName = groupRepository.findGroupByName(groupName);
        University foundedUniversityByUniversityName = universityRepository.findUniversityByUniversityName(universityName);
        if (foundedUserByUsername == null ||
            foundedGroupByGroupName == null ||
            foundedUniversityByUniversityName == null) {
            throw new ScheduleInformationIncorrectException("Data is not found. Need correct information.");
        }
        Schedule currentSchedule = scheduleRepository.save(schedule);

        foundedUserByUsername.addSchedule(currentSchedule);
        foundedGroupByGroupName.addSchedule(currentSchedule);
        foundedUniversityByUniversityName.addSchedule(currentSchedule);
    }

    @Override
    @Transactional
    public List<Schedule> findAllByGroup(String groupName, String universityName, LocalDate startDay, LocalDate endDay) {
        checkUniversityContains(universityName, startDay, endDay);
        List<Schedule> groups = scheduleRepository.findAllByGroupNameAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(groupName, universityName, startDay, currentDate(startDay, endDay));
        if (groups.isEmpty()) {
            throw new InformationNotFoundException("Group with name: {" + groupName + "} is not found.");
        }
        return groups;
    }

    @Override
    @Transactional
    public List<Schedule> findAllByLectureRoom(String lectureRoom, String universityName, LocalDate startDay, LocalDate endDay) {
        checkUniversityContains(universityName, startDay, endDay);
        List<Schedule> lectureRooms = scheduleRepository.findAllByLectureRoomAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(lectureRoom, universityName, startDay, currentDate(startDay, endDay));
        if (lectureRooms.isEmpty()) {
            throw new InformationNotFoundException("Lecture room with name: {" + lectureRoom + "} is not found.");
        }
        return lectureRooms;
    }

    @Override
    @Transactional
    public List<Schedule> findAllByTeacher(String teacherName, String teacherSurname, String teacherPatronymic, String universityName, LocalDate startDay, LocalDate endDay) {
        checkUniversityContains(universityName, startDay, endDay);
        List<Schedule> teachers = scheduleRepository.findAllByUser_NameAndUser_SurnameAndUser_PatronymicAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(teacherName, teacherSurname, teacherPatronymic, universityName, startDay, currentDate(startDay, endDay));
        if (teachers.isEmpty()) {
            throw new InformationNotFoundException("Teacher is not found.");
        }
        return teachers;
    }

    @Override
    @Transactional
    public List<Schedule> findAllByTypeLecture(String typeLecture, String universityName, LocalDate startDay, LocalDate endDay) {
        checkUniversityContains(universityName, startDay, endDay);
        List<Schedule> lectures = scheduleRepository.findAllByTypeLectureAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(typeLecture, universityName, startDay, currentDate(startDay, endDay));
        if (lectures.isEmpty()) {
            throw new InformationNotFoundException("Lecture type with name: {" + typeLecture + "} is not found.");
        }
        return lectures;
    }

    @Override
    @Transactional
    public List<Schedule> findAllByUniversity(String universityName, LocalDate startDay, LocalDate endDay) {
        List<Schedule> universities = scheduleRepository.findAllByUniversity_UniversityNameAndDateBetweenOrderByDateAsc(universityName, startDay, currentDate(startDay, endDay));
        if (universities.isEmpty()) {
            throw new InformationNotFoundException("University with name: {" + universityName +"} is not found.");
        }
        return universities;
    }

    @Override
    @Transactional
    public List<Schedule> findAllByTime(LocalTime startTime, LocalTime endTime, String universityName, LocalDate startDay, LocalDate endDay) {
        endTime = (endTime == null) ? startTime : endTime;
        checkUniversityContains(universityName, startDay, endDay);
        return  scheduleRepository.findAllByStartLectureBetweenAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(startTime, endTime, universityName, startDay, currentDate(startDay, endDay));
    }

    // FIXME: 06.05.2020 for > for > for
    @Override
    public ScheduleControlPanel getScheduleControlPanel() {
        ScheduleControlPanel scheduleControlPanel = new ScheduleControlPanel();
        List<University> universities = universityRepository.findAll();
        List<UserScheduleDto> users = userService.findUsersByRoleName(SecurityRole.TEACHER.name());

        List<GroupUniversityDto> groupUniversityDtos = new ArrayList<>();
        for (University university : universities) {
            GroupUniversityDto groupUniversityDto = new GroupUniversityDto();
            UniversityDto universityDto = new UniversityDto();

            List<GroupDto> groupDtos = new ArrayList<>();
            for (Faculty faculty : university.getFaculties()) {
                for (Group group : faculty.getGroups()) {
                    GroupDto groupDto = new GroupDto();
                    groupDto.setName(group.getName());
                    groupDtos.add(groupDto);
                }
            }

            universityDto.setUniversityName(university.getUniversityName());
            groupUniversityDto.setGroups(groupDtos);
            groupUniversityDto.setUniversity(universityDto);

            groupUniversityDtos.add(groupUniversityDto);
        }

        scheduleControlPanel.setUsers(users);
        scheduleControlPanel.setUniversities(groupUniversityDtos);

        return scheduleControlPanel;
    }

    @Override
    public List<Schedule> findSchedules(String groupName,
                                        String lectureRoom,
                                        String teacherName, String teacherSurname, String teacherPatronymic,
                                        String typeLecture,
                                        String universityName,
                                        LocalDate startDay, LocalDate endDay,
                                        LocalTime startTime, LocalTime endTime) {
        if (startDay != null) {
            if (!groupName.isEmpty()
                    && !universityName.isEmpty()) {
                return findAllByGroup(groupName, universityName, startDay, endDay);
            }

            if (!lectureRoom.isEmpty()
                    && !universityName.isEmpty()) {
                return findAllByLectureRoom(lectureRoom, universityName, startDay, endDay);
            }


            if (!teacherName.isEmpty() && !teacherSurname.isEmpty() && !teacherPatronymic.isEmpty()
                    && !universityName.isEmpty()) {
                return findAllByTeacher(teacherName, teacherSurname, teacherPatronymic, universityName, startDay, endDay);
            }

            if (!typeLecture.isEmpty()
                    && !universityName.isEmpty()) {
                return findAllByTypeLecture(typeLecture, universityName, startDay, endDay);
            }

            if (startTime != null) {
                if (!universityName.isEmpty()) {
                    return findAllByTime(startTime, endTime, universityName, startDay, endDay);
                }
            }

            if (!universityName.isEmpty()) {
                return findAllByUniversity(universityName, startDay, endDay);
            }
        }

        return new ArrayList<>();
    }

    public List<ScheduleDto> convertScheduleToScheduleDto(List<Schedule> schedules) {
        if (schedules.isEmpty()) {
            throw new CustomInformationNotFoundException("Information not found");
        }
        ScheduleDto scheduleDto = new ScheduleDto();
        return scheduleDto.listSchedulesToListSchedulesDto(schedules);
    }

    private LocalDate currentDate(LocalDate startDay, LocalDate endDay) {
        return (endDay == null) ? startDay : endDay;
    }

    private void checkUniversityContains(String universityName, LocalDate startDay, LocalDate endDay) {
        List<Schedule> universities = findAllByUniversity(universityName, startDay, endDay);
        if (universities.isEmpty()) {
            throw new InformationNotFoundException("University with name: {" + universityName +"} is not found.");
        }
    }

}
