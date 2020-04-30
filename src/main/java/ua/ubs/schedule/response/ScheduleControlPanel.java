package ua.ubs.schedule.response;

import ua.ubs.schedule.dto.GroupDto;
import ua.ubs.schedule.dto.UniversityScheduleDto;
import ua.ubs.schedule.dto.UserDto;

import java.util.List;

public class ScheduleControlPanel {

    private List<UserDto> users;
    private List<GroupDto> groups;
    private List<UniversityScheduleDto> universities;

    public ScheduleControlPanel() {
    }

    public ScheduleControlPanel(List<UserDto> users, List<GroupDto> groups, List<UniversityScheduleDto> universities) {
        this.users = users;
        this.groups = groups;
        this.universities = universities;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }

    public List<UniversityScheduleDto> getUniversities() {
        return universities;
    }

    public void setUniversities(List<UniversityScheduleDto> universities) {
        this.universities = universities;
    }

    @Override
    public String toString() {
        return "ScheduleControlPanel{" +
                "users=" + users +
                ", groups=" + groups +
                ", universities=" + universities +
                '}';
    }

}
