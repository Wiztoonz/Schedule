package ua.ubs.schedule.service.serviceImpl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.dto.FacultyDto;
import ua.ubs.schedule.dto.FacultyUniversityDto;
import ua.ubs.schedule.dto.UniversityDto;
import ua.ubs.schedule.entity.Faculty;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.repository.FacultyRepository;
import ua.ubs.schedule.repository.UniversityRepository;
import ua.ubs.schedule.service.FacultyService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    @Transactional
    public void setFaculty(String universityName, Faculty faculty) {
        University university = universityRepository.findUniversityByUniversityName(universityName);
        if (university == null) {
            throw new InformationNotFoundException("University is not found.");
        }
        boolean contains = false;
        for (Faculty universityFaculty : university.getFaculties()) {
            if (universityFaculty.getName().equals(faculty.getName())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            university.addFaculty(faculty);
            facultyRepository.save(faculty);
        } else {
            throw new ConstraintViolationException("This faculty already exists", new SQLException(), "name");
        }
    }

    @Override
    @Transactional
    public void deleteFaculty(String facultyName) {
        Faculty faculty = facultyRepository.findByName(facultyName);
        University university = universityRepository.findUniversityById(faculty.getId());
        university.removeFaculty(faculty);
        facultyRepository.delete(faculty);
    }

    @Override
    public List<FacultyUniversityDto> findAllFaculties() {
        List<University> universities = universityRepository.findAll();
        if (universities.isEmpty()) {
            throw new InformationNotFoundException("Information not found!");
        }
        List<FacultyUniversityDto> facultyUniversityDtos = new ArrayList<>();

        for (University university : universities) {
            FacultyUniversityDto facultyUniversityDto = new FacultyUniversityDto();
            UniversityDto universityDto = new UniversityDto();

            List<FacultyDto> facultyDtos = new ArrayList<>();

            for (Faculty faculty : university.getFaculties()) {
                FacultyDto facultyDto = new FacultyDto();
                facultyDto.setFacultyName(faculty.getName());
                facultyDtos.add(facultyDto);
            }

            universityDto.setUniversityName(university.getUniversityName());

            facultyUniversityDto.setFaculties(facultyDtos);
            facultyUniversityDto.setUniversity(universityDto);

            facultyUniversityDtos.add(facultyUniversityDto);
        }

        return facultyUniversityDtos;
    }

}
