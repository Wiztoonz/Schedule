package ua.ubs.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ubs.schedule.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findByUsername(String username);

}
