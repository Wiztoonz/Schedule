package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"university", "faculties"})
public class FacultyUniversityDto {

    @JsonProperty("faculties")
    private List<FacultyDto> faculties;
    @JsonProperty("university")
    private UniversityDto university;

    public FacultyUniversityDto() {
    }

    public FacultyUniversityDto(List<FacultyDto> faculties, UniversityDto university) {
        this.faculties = faculties;
        this.university = university;
    }

    public List<FacultyDto> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<FacultyDto> faculties) {
        this.faculties = faculties;
    }

    public UniversityDto getUniversity() {
        return university;
    }

    public void setUniversity(UniversityDto university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "FacultyUniversityDto{" +
                "faculties=" + faculties +
                ", university=" + university +
                '}';
    }

}
