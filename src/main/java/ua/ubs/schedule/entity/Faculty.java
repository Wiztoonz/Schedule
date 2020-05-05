package ua.ubs.schedule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_faculty_id")
    private Long id;
    @Column(name = "_faculty_name", unique = true)
    @JsonProperty(value = "name")
    @NotBlank(message = "Can not be empty!")
    private String name;

    @ManyToOne
    @JoinColumn(name = "_university_id", referencedColumnName = "_university_id")
    @JsonProperty(value = "universityId")
    private University university;

    @ManyToMany(mappedBy = "faculties")
    @JsonIgnore
    private List<Group> groups = new ArrayList<>();

    public Faculty() {
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university=" + university +
                ", groups=" + groups +
                '}';
    }
}
