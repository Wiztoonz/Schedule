package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.ubs.schedule.entity.User;

@JsonPropertyOrder({"name", "surname", "patronymic"})
public class UserDto {

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "patronymic")
    private String patronymic;

    public UserDto() {
    }

    public UserDto userToUserDto(User user) {
        setName(user.getName());
        setSurname(user.getSurname());
        setPatronymic(user.getPatronymic());
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

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
