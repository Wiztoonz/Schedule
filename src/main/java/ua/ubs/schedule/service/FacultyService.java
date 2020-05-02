package ua.ubs.schedule.service;

import ua.ubs.schedule.dto.FacultyUniversityDto;
import ua.ubs.schedule.entity.Faculty;

import java.util.List;

public interface FacultyService {

    void setFaculty(String universityName, Faculty faculty);

    void deleteFaculty(String facultyName);

    List<FacultyUniversityDto> findAllFaculties();

}
