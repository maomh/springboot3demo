package site.mhjn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.mhjn.demo.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
