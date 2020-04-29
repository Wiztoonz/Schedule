package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.security.access.AppAccess;

@JsonPropertyOrder({"name", "surname", "patronymic, username"})
public class UserDto implements Comparable<UserDto> {

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "patronymic")
    private String patronymic;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "appAccess")
    private AppAccess appAccess;

    public UserDto() {
    }

    public UserDto userToUserDto(User user) {
        setName(user.getName());
        setSurname(user.getSurname());
        setPatronymic(user.getPatronymic());
        setUsername(user.getUsername());
        return this;
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

    public AppAccess getAppAccess() {
        return appAccess;
    }

    public void setAppAccess(AppAccess appAccess) {
        this.appAccess = appAccess;
    }

    @Override
    public int compareTo(UserDto userDto) {
        return this.name.compareTo(userDto.name);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", username='" + username + '\'' +
                ", appAccess=" + appAccess +
                '}';
    }
}
