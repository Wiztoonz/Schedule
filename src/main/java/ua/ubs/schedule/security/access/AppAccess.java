package ua.ubs.schedule.security.access;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppAccess {

    @JsonProperty(value = "managerAccess", defaultValue = "false")
    private boolean managerAccess;
    @JsonProperty(value = "adminAccess", defaultValue = "false")
    private boolean adminAccess;
    @JsonProperty(value = "teacherAccess", defaultValue = "false")
    private boolean teacherAccess;
    @JsonProperty(value = "userAccess", defaultValue = "false")
    private boolean userAccess;

    public AppAccess() {
    }

    public AppAccess(boolean managerAccess, boolean adminAccess, boolean teacherAccess, boolean userAccess) {
        this.managerAccess = managerAccess;
        this.adminAccess = adminAccess;
        this.teacherAccess = teacherAccess;
        this.userAccess = userAccess;
    }

    public boolean isManagerAccess() {
        return managerAccess;
    }

    public void setManagerAccess(boolean managerAccess) {
        this.managerAccess = managerAccess;
    }

    public boolean isAdminAccess() {
        return adminAccess;
    }

    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }

    public boolean isTeacherAccess() {
        return teacherAccess;
    }

    public void setTeacherAccess(boolean teacherAccess) {
        this.teacherAccess = teacherAccess;
    }

    public boolean isUserAccess() {
        return userAccess;
    }

    public void setUserAccess(boolean userAccess) {
        this.userAccess = userAccess;
    }

    @Override
    public String toString() {
        return "AppAccess{" +
                "managerAccess=" + managerAccess +
                ", adminAccess=" + adminAccess +
                ", teacherAccess=" + teacherAccess +
                ", userAccess=" + userAccess +
                '}';
    }

}
