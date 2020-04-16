package ua.ubs.schedule.service;

import ua.ubs.schedule.entity.Faculty;

public interface FacultyService {

    void setFaculty(String universityName, Faculty faculty);

    void deleteFaculty(String facultyName);

}
