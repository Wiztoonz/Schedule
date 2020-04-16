package ua.ubs.schedule.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.ubs.schedule.entity.Role;
import ua.ubs.schedule.entity.User;
import ua.ubs.schedule.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        ApplicationUser applicationUser = new ApplicationUser(user.getId(), user.getUsername(), user.getPassword(), authorities);
        return new UserPrincipal(applicationUser);
    }

}
