package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacultyDto {

    @JsonProperty("facultyName")
    private String facultyName;


    public FacultyDto() {
    }

    public FacultyDto(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "FacultyDto{" +
                "facultyName='" + facultyName + '\'' +
                '}';
    }

}
