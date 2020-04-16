package ua.ubs.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ubs.schedule.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findGroupByName(String groupName);

}
