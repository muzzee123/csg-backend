package za.co.csg.assessment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.csg.assessment.model.Students;

import java.util.stream.Stream;

public interface StudentsRepository extends JpaRepository<Students, Long> {

    Stream<Students> findAllByClassName(Pageable pageable, String className);
}
