package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.ubs.schedule.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "subjectName", "typeLecture",
        "lectureRoom", "group", "teacher", "university",
        "date","startLecture", "finishLecture"})
public class ScheduleDto {

    @JsonProperty(value = "subjectName")
    private String subjectName;
    @JsonProperty(value = "typeLecture")
    private String typeLecture;
    @JsonProperty(value = "date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    @JsonProperty(value = "startLecture")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startLecture;
    @JsonProperty(value = "finishLecture")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime finishLecture;
    @JsonProperty(value = "group")
    private GroupDto group;
    @JsonProperty(value = "teacher")
    private UserScheduleDisplayDto user;
    @JsonProperty(value = "university")
    private UniversityScheduleDto university;
    @JsonProperty(value = "lectureRoom")
    private String lectureRoom;

    public ScheduleDto() {
    }

    public List<ScheduleDto> listSchedulesToListSchedulesDto(List<Schedule> schedules) {
        List<ScheduleDto> scheduleDto = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleDto dto = new ScheduleDto();
            UserScheduleDisplayDto userDto = new UserScheduleDisplayDto();
            GroupDto groupDto = new GroupDto();
            UniversityScheduleDto universityScheduleDto = new UniversityScheduleDto();

            groupDto.groupToGroupDto(schedule.getGroup());
            userDto.userToUserScheduleDisplayDto(schedule.getUser());
            universityScheduleDto.universityToUniversityScheduleDto(schedule.getUniversity());

            dto.setSubjectName(schedule.getSubjectName());
            dto.setTypeLecture(schedule.getTypeLecture());
            dto.setDate(schedule.getDate());
            dto.setStartLecture(schedule.getStartLecture());
            dto.setFinishLecture(schedule.getFinishLecture());
            dto.setLectureRoom(schedule.getLectureRoom());
            dto.setUser(userDto);
            dto.setGroup(groupDto);
            dto.setUniversity(universityScheduleDto);

            scheduleDto.add(dto);
        }

        return scheduleDto;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTypeLecture() {
        return typeLecture;
    }

    public void setTypeLecture(String typeLecture) {
        this.typeLecture = typeLecture;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartLecture() {
        return startLecture;
    }

    public void setStartLecture(LocalTime startLecture) {
        this.startLecture = startLecture;
    }

    public LocalTime getFinishLecture() {
        return finishLecture;
    }

    public void setFinishLecture(LocalTime finishLecture) {
        this.finishLecture = finishLecture;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public UserScheduleDisplayDto getUser() {
        return user;
    }

    public void setUser(UserScheduleDisplayDto user) {
        this.user = user;
    }

    public UniversityScheduleDto getUniversity() {
        return university;
    }

    public void setUniversity(UniversityScheduleDto university) {
        this.university = university;
    }

    public String getLectureRoom() {
        return lectureRoom;
    }

    public void setLectureRoom(String lectureRoom) {
        this.lectureRoom = lectureRoom;
    }

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "subjectName='" + subjectName + '\'' +
                ", typeLecture='" + typeLecture + '\'' +
                ", date=" + date +
                ", startLecture=" + startLecture +
                ", finishLecture=" + finishLecture +
                ", group=" + group +
                ", user=" + user +
                ", lectureRoom='" + lectureRoom + '\'' +
                '}';
    }

}
