package ua.ubs.schedule.auth;

import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class ApplicationUser {

    private Long id;
    @NotBlank(message = "username can not be empty")
    private String username;
    @NotBlank(message = "password can not be empty")
    private String password;
    private Set<GrantedAuthority> authorities;

    public ApplicationUser() {
    }

    public ApplicationUser(Long id, String username, String password, Set<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
