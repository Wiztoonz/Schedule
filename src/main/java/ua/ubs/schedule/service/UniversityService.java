package ua.ubs.schedule.service;

import ua.ubs.schedule.dto.UniversityDto;
import ua.ubs.schedule.dto.UniversityScheduleDto;
import ua.ubs.schedule.entity.University;

import java.util.List;

public interface UniversityService {

    void addUniversity(University university);

    void updateUniversityByName(String universityName, University university);

    List<UniversityScheduleDto> findAll();

    List<UniversityDto> findAllUniversities();
}
