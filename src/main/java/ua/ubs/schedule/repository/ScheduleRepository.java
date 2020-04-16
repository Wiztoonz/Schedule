package ua.ubs.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ubs.schedule.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByGroupNameAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(String groupName, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByLectureRoomAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(String lectureRoom, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByUser_NameAndUser_SurnameAndUser_PatronymicAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(String teacherName, String teacherSurname, String teacherPatronymic, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByTypeLectureAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(String typeLecture, String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByUniversity_UniversityNameAndDateBetweenOrderByDateAsc(String universityName, LocalDate startDay, LocalDate endDay);

    List<Schedule> findAllByStartLectureBetweenAndUniversity_UniversityNameAndDateBetweenOrderByDateAsc(LocalTime startTime, LocalTime endTime, String universityName, LocalDate startDay, LocalDate endDay);

}
