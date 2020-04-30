package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.ubs.schedule.entity.User;

@JsonPropertyOrder({"name", "surname", "patronymic", "username"})
public class UserScheduleDto implements Comparable<UserScheduleDto> {

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "patronymic")
    private String patronymic;
    @JsonProperty(value = "username")
    private String username;

    public UserScheduleDto() {
    }

    public UserScheduleDto userToUserDto(User user) {
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

    @Override
    public int compareTo(UserScheduleDto userScheduleDto) {
        return this.name.compareTo(userScheduleDto.name);
    }

    @Override
    public String toString() {
        return "UserScheduleDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
