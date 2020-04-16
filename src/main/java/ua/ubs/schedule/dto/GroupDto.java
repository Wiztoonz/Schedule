package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.ubs.schedule.entity.Group;

@JsonPropertyOrder({"name"})
public class GroupDto {

    @JsonProperty(value = "name")
    private String name;

    public GroupDto() {
    }

    public GroupDto groupToGroupDto(Group group) {
        setName(group.getName());
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
