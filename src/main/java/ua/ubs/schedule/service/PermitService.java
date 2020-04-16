package ua.ubs.schedule.service;

import ua.ubs.schedule.entity.Role;

public interface PermitService {

    void setUserPermit(String username, Role role);

    void deleteUserPermit(String username, Role role);

}
