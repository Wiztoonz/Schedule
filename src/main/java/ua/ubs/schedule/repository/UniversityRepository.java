package ua.ubs.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ubs.schedule.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    University findUniversityByUniversityName(String universityName);

    University findUniversityById(Long id);

}
