package ua.ubs.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.ubs.schedule.entity.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleDto {

    @JsonProperty("role")
    private String role;

    public RoleDto roleToRoleDto(Role role) {
        setRole(role.getName());
        return this;
    }

    public RoleDto() {
    }

    public RoleDto(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "role='" + role + '\'' +
                '}';
    }

}