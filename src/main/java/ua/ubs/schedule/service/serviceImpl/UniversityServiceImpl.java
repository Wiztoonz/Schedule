package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.dto.UniversityDto;
import ua.ubs.schedule.dto.UniversityScheduleDto;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.repository.UniversityRepository;
import ua.ubs.schedule.service.UniversityService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public void addUniversity(University university) {
        try {
            universityRepository.save(university);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("This university already exists");
        }
    }

    @Override
    @Transactional
    public void updateUniversityByName(String universityName, University university) {
        University foundUniversityByName = universityRepository.findUniversityByUniversityName(universityName);
        foundUniversityByName.setUniversityName(university.getUniversityName());
        foundUniversityByName.setAddress(university.getAddress());
        foundUniversityByName.setLocation(university.getLocation());
    }

    @Override
    public List<UniversityScheduleDto> findAll() {
        List<University> universities = universityRepository.findAll();
        List<UniversityScheduleDto> universityScheduleDtos = new ArrayList<>();

        for (University university : universities) {
            UniversityScheduleDto universityScheduleDto = new UniversityScheduleDto();
            UniversityScheduleDto dto = universityScheduleDto.universityToUniversityScheduleDto(university);
            universityScheduleDtos.add(dto);
        }

        return universityScheduleDtos;
    }

    @Override
    public List<UniversityDto> findAllUniversities() {
        List<University> universities = universityRepository.findAll();
        List<UniversityDto> universityDtos = new ArrayList<>();
        for (University university : universities) {
            UniversityDto universityDto = new UniversityDto();
            universityDto.setUniversityName(university.getUniversityName());
            universityDtos.add(universityDto);
        }
        return universityDtos;
    }

}
