package ua.ubs.schedule.service;

import ua.ubs.schedule.entity.University;

public interface UniversityService {

    void addUniversity(University university);

    void updateUniversityByName(String universityName, University university);

}
