package nu.edu.education.repositories;

import nu.edu.education.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEmail(String email);

    Set<Student> findAllById(Long id);
    Optional<Student> findStudentByUsername(String username);
}
