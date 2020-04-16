package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.entity.Faculty;
import ua.ubs.schedule.entity.Group;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.repository.GroupRepository;
import ua.ubs.schedule.repository.UniversityRepository;
import ua.ubs.schedule.service.GroupService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UniversityRepository universityRepository) {
        this.groupRepository = groupRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    @Transactional
    public void addGroup(String universityName, String facultyName, Group group) {
        University foundUniversityByUniversityName = universityRepository.findUniversityByUniversityName(universityName);
        List<Faculty> faculties = foundUniversityByUniversityName.getFaculties();

        for (Faculty faculty : faculties) {
            String name = faculty.getName();
            if (name.equals(facultyName)) {
                Group currentGroup = groupRepository.save(group);
                currentGroup.addFaculty(faculty);
            }
        }
    }

}
