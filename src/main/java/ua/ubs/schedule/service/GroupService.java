package ua.ubs.schedule.service;

import ua.ubs.schedule.entity.Group;

public interface GroupService {

    void addGroup(String universityName, String facultyName, Group group);

}
