package ua.ubs.schedule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_schedule_id")
    private Long id;
    @Column(name = "_subjectName")
    @JsonProperty(value = "subjectName")
    @NotBlank(message = "Can not be empty!")
    private String subjectName;
    @Column(name = "_typeLecture")
    @JsonProperty(value = "typeLecture")
    @NotBlank(message = "Can not be empty!")
    private String typeLecture;
    @Column(name = "_date", columnDefinition = "DATE")
    @JsonProperty(value = "date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @Column(name = "_startLecture", columnDefinition = "TIME")
    @JsonProperty(value = "startLecture")
    @JsonFormat(pattern = "HH:mm")
    @NotBlank(message = "Can not be empty!")
    private LocalTime startLecture;
    @Column(name = "_finishLecture", columnDefinition = "TIME")
    @JsonProperty(value = "finishLecture")
    @JsonFormat(pattern = "HH:mm")
    @NotBlank(message = "Can not be empty!")
    private LocalTime finishLecture;

    @ManyToOne
    @JoinColumn(name = "_group_id", referencedColumnName = "_group_id")
    @JsonProperty(value = "group")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "_user_id", referencedColumnName = "_user_id")
    @JsonProperty(value = "teacher")
    private User user;

    @ManyToOne
    @JoinColumn(name = "_university_id", referencedColumnName = "_university_id")
    @JsonProperty(value = "university")
    private University university;

    @Column(name = "_lectureRoom")
    @JsonProperty(value = "lectureRoom")
    private String lectureRoom;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTypeLecture() {
        return typeLecture;
    }

    public void setTypeLecture(String typeLecture) {
        this.typeLecture = typeLecture;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLectureRoom() {
        return lectureRoom;
    }

    public void setLectureRoom(String lectureRoom) {
        this.lectureRoom = lectureRoom;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", date=" + date +
                ", group=" + group +
                ", user=" + user +
                ", lectureRoom='" + lectureRoom + '\'' +
                '}';
    }
}
