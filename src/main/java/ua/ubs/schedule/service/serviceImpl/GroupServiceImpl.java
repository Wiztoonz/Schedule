package ua.ubs.schedule.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.dto.GroupDto;
import ua.ubs.schedule.entity.Faculty;
import ua.ubs.schedule.entity.Group;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.exaption.InformationNotFoundException;
import ua.ubs.schedule.repository.GroupRepository;
import ua.ubs.schedule.repository.UniversityRepository;
import ua.ubs.schedule.service.GroupService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        if (foundUniversityByUniversityName == null) {
            throw new InformationNotFoundException("This university is not found.");
        }
        List<Faculty> faculties = foundUniversityByUniversityName.getFaculties();
        if (faculties.isEmpty()) {
            throw new InformationNotFoundException("This faculty is not found.");
        }

        for (Faculty faculty : faculties) {
            for (Group facultyGroup : faculty.getGroups()) {
                if (facultyGroup.getName().equals(group.getName())) {
                    throw new DataIntegrityViolationException("This faculty already exists this group.");
                }
            }

            String name = faculty.getName();
            if (name.equals(facultyName)) {
                Group currentGroup = groupRepository.save(group);
                currentGroup.addFaculty(faculty);
            }
        }
    }

    @Override
    public List<GroupDto> findAll() {
        List<Group> groups = groupRepository.findAll();
        List<GroupDto> groupDtos = new ArrayList<>();

        for (Group group : groups) {
            GroupDto groupDto = new GroupDto();
            GroupDto dto = groupDto.groupToGroupDto(group);
            groupDtos.add(dto);
        }

        return groupDtos;
    }

}
