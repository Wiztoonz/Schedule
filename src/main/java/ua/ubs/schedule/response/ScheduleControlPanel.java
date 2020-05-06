package ua.ubs.schedule.response;

import ua.ubs.schedule.dto.GroupUniversityDto;
import ua.ubs.schedule.dto.UserScheduleDto;

import java.util.List;

public class ScheduleControlPanel {

    private List<UserScheduleDto> users;
    private List<GroupUniversityDto> universities;

    public ScheduleControlPanel() {
    }

    public ScheduleControlPanel(List<UserScheduleDto> users, List<GroupUniversityDto> universities) {
        this.users = users;
        this.universities = universities;
    }

    public List<UserScheduleDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserScheduleDto> users) {
        this.users = users;
    }

    public List<GroupUniversityDto> getUniversities() {
        return universities;
    }

    public void setUniversities(List<GroupUniversityDto> universities) {
        this.universities = universities;
    }

    @Override
    public String toString() {
        return "ScheduleControlPanel{" +
                "users=" + users +
                ", universities=" + universities +
                '}';
    }

}

