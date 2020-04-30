package ua.ubs.schedule.service;

import ua.ubs.schedule.dto.GroupDto;
import ua.ubs.schedule.entity.Group;

import java.util.List;

public interface GroupService {

    void addGroup(String universityName, String facultyName, Group group);

    List<GroupDto> findAll();

}
