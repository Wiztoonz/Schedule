package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;

@JsonPropertyOrder({"name", "surname", "patronymic", "username", "roles"})
public class UserRoleDto {

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "patronymic")
    private String patronymic;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "roles")
    private Set<RoleDto> roles;

    public UserRoleDto() {
    }

    public UserRoleDto(String name, String surname, String patronymic, String username, Set<RoleDto> roles) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.username = username;
        this.roles = roles;
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

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserRoleDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }

}
