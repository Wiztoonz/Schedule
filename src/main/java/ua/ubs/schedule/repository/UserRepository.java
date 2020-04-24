package ua.ubs.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ubs.schedule.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findByUsername(String username);

    List<User> findAllByNameAndSurnameAndPatronymicOrderByName(String name, String surname, String patronymic);

}
