package ua.ubs.schedule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_university_id")
    private Long id;
    @Column(name = "_university_name", unique = true)
    @JsonProperty(value = "universityName")
    private String universityName;
    @Column(name = "_location")
    @JsonProperty(value = "location")
    private String location;
    @Column(name = "_address")
    @JsonProperty(value = "address")
    private String address;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Faculty> faculties = new ArrayList<>();

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
        faculty.setUniversity(this);
    }

    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
        faculty.setUniversity(null);
    }

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private Set<Schedule> schedules = new HashSet<>();

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        schedule.setUniversity(this);
    }

    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
        schedule.setUniversity(null);
    }

    public University() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", universityName='" + universityName + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
