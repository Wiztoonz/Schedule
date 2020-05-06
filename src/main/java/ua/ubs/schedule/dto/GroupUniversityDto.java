package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"university", "groups"})
public class GroupUniversityDto {

    @JsonProperty("groups")
    private List<GroupDto> groups;
    @JsonProperty("university")
    private UniversityDto university;

    public GroupUniversityDto() {
    }

    public GroupUniversityDto(List<GroupDto> groups, UniversityDto university) {
        this.groups = groups;
        this.university = university;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }

    public UniversityDto getUniversity() {
        return university;
    }

    public void setUniversity(UniversityDto university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "GroupUniversityDto{" +
                "groups=" + groups +
                ", university=" + university +
                '}';
    }

}
