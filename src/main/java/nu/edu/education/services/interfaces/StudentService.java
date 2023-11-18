package nu.edu.education.services.interfaces;

import nu.edu.education.dto.StudentMarksDTO;
import nu.edu.education.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student addNewStudent(Student student);

    public List<Student> getAll();

    public Optional<Student> findStudentById(Long studendId);
    public void deleteStudent(Long studentid);
    public void updateStudent(Long studentId, String name, String surname, String email, String username);
    public void markGrades(StudentMarksDTO dto);
}
