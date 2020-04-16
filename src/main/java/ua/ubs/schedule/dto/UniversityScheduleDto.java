package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.ubs.schedule.entity.University;

@JsonPropertyOrder({"universityName"})
public class UniversityScheduleDto {

    @JsonProperty(value = "universityName")
    private String universityName;

    public UniversityScheduleDto() {
    }

    public UniversityScheduleDto universityToUniversityScheduleDto(University university) {
        setUniversityName(university.getUniversityName());
        return this;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "UniversityScheduleDto{" +
                "universityName='" + universityName + '\'' +
                '}';
    }
}
