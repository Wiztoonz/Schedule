package ua.ubs.schedule.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_user_id")
    private Long id;
    @Column(name = "_user_name")
    @JsonProperty(value = "name")
    @Size(message = "Need 2 or more chars! {min=2} {max=20}", min = 2, max = 20)
    private String name;
    @Column(name = "_surname")
    @JsonProperty(value = "surname")
    @Size(message = "Need 2 or more chars! {min=2} {max=20}", min = 2, max = 20)
    private String surname;
    @Column(name = "_patronymic")
    @JsonProperty(value = "patronymic")
    @Size(message = "Need 2 or more chars! {min=2} {max=20}", min = 2, max = 20)
    private String patronymic;
    @Column(name = "_username", unique = true, nullable = false)
    @JsonProperty(value = "username")
    @Size(message = "Need 2 or more chars! {min=2} {max=20}", min = 2, max = 20)
    private String username;
    @Column(name = "_password", nullable = false)
    @JsonProperty(value = "password")
    @Size(message = "Need 8 or more! {min=8}", min = 8)
    private String password;

    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Schedule> schedules = new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        schedule.setUser(this);
    }

    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
        schedule.setUser(null);
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "_users_roles",
        joinColumns = @JoinColumn(name = "_user_id", referencedColumnName = "_user_id"),
        inverseJoinColumns = @JoinColumn(name = "_role_id", referencedColumnName = "_role_id"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public User() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }
}
