package ua.ubs.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ubs.schedule.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findByName(String facultyName);

}
