package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.entity.Faculty;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.repository.FacultyRepository;
import ua.ubs.schedule.repository.UniversityRepository;
import ua.ubs.schedule.service.FacultyService;

import javax.transaction.Transactional;

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
        university.addFaculty(faculty);
        facultyRepository.save(faculty);
    }

    @Override
    @Transactional
    public void deleteFaculty(String facultyName) {
        Faculty faculty = facultyRepository.findByName(facultyName);
        University university = universityRepository.findUniversityById(faculty.getId());
        university.removeFaculty(faculty);
        facultyRepository.delete(faculty);
    }

}
