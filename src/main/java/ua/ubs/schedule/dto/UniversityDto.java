package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.ubs.schedule.entity.University;

public class UniversityDto {

    @JsonProperty("universityName")
    private String universityName;

    public UniversityDto universityToUniversityDto(University university) {
        setUniversityName(university.getUniversityName());
        return this;
    }

    public UniversityDto() {
    }

    public UniversityDto(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "UniversityDto{" +
                "universityName='" + universityName + '\'' +
                '}';
    }
}
