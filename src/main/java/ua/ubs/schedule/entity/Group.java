package ua.ubs.schedule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_group_id")
    private Long id;
    @Column(name = "_group_name")
    @JsonProperty(value = "name")
    @NotBlank(message = "Can not be empty!")
    private String name;

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private Set<Schedule> schedules = new HashSet<>();

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        schedule.setGroup(this);
    }

    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
        schedule.setGroup(null);
    }

    @ManyToMany
    @JoinTable(name = "_faculties_groups",
        joinColumns = @JoinColumn(name = "_group_id", referencedColumnName = "_group_id"),
        inverseJoinColumns = @JoinColumn(name = "_faculty_id", referencedColumnName = "_faculty_id"))
    @JsonIgnore
    private List<Faculty> faculties = new ArrayList<>();

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
        faculty.getGroups().add(this);
    }

    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
        faculty.getGroups().remove(this);
    }

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

}
