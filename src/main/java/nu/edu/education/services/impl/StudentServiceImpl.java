package nu.edu.education.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nu.edu.education.dto.CourseRegistrationDTO;
import nu.edu.education.dto.StudentMarksDTO;
import nu.edu.education.entities.Course;
import nu.edu.education.entities.Grades;
import nu.edu.education.entities.Student;
import nu.edu.education.repositories.CourseRepository;
import nu.edu.education.repositories.GradesRepository;
import nu.edu.education.repositories.StudentRepository;
import nu.edu.education.services.interfaces.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GradesRepository gradesRepository;
    private final CourseRepository courseRepository;
    private final nu.edu.education.services.impl.GradesServiceImpl gradesService;

    public Student addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Long studendId) {
        boolean exists = studentRepository.existsById(studendId);
        if (!exists) {
            throw new IllegalStateException("student with id: " + studendId + " does not exist");
        }
        return studentRepository.findById(studendId);
    }

    public void deleteStudent(Long studentid) {
        boolean exitst = studentRepository.existsById(studentid);
        if (!exitst) {
            throw new IllegalStateException("student with id: " + studentid + "doesn't exist");
        }
        studentRepository.deleteById(studentid);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String surname, String email, String username) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student with id: " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (surname != null && surname.length() > 0 && !Objects.equals(student.getSurname(), surname)) {
            student.setSurname(surname);
        }

        if (email != null && email.length() > 0 && !Objects.equals((student.getEmail()), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email is not available");
            }
            student.setEmail(email);
        }

        if (username != null && username.length() > 0 && !Objects.equals(student.getUsername(), username)) {
            Optional<Student> stringOptional1 = studentRepository.findStudentByUsername(username);
            if (stringOptional1.isPresent()) {
                throw new IllegalStateException("username is not available");
            }
            student.setUsername(username);
        }
    }

    public void markGrades(StudentMarksDTO dto) {
//        Student student1 = studentRepository.findById(dto.getStudId()).orElseThrow(() ->
//                new IllegalStateException("student with id: " + dto.getStudId() + "does not exist"));
//        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow(() ->
//                new IllegalStateException("course with id: " + dto.getCourseId() + "does not exist"));
//        Set<Course> courseSet = courseRepository.findAllById(dto.getCourseId());
//        Set<Student> studentSet = studentRepository.findAllById(dto.getStudId());
//        for (Grades grades : dto.getGrades()) {
//            grades.setFinal_grade(gradesService.calculateFinalGrade(grades));
//        }
//        student1.setCourses(courseSet);
//        course.setStudents(studentSet);
//        student1.setStud_grade(dto.getGrades());
//        course.setCourse_grade(dto.getGrades());
//        studentRepository.save(student1);
//        courseRepository.save(course);

        Grades grades = new Grades();
        Student student1 = studentRepository.findById(dto.getStudId()).orElseThrow(() ->
                new IllegalStateException("student with id: " + dto.getStudId() + "does not exist"));
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow(() ->
                new IllegalStateException("course with id: " + dto.getCourseId() + "does not exist"));

        if(!student1.getCourses().contains(course)){
            throw new IllegalStateException("This student with id: " + student1.getId()+ " doesn't have this course with id: " + course.getId());
        }

        grades.setStudent(student1);
        grades.setCourse(course);
        grades.setQuizzes(dto.getGrades().getQuizzes());
        grades.setAttendance(dto.getGrades().getAttendance());
        grades.setMidterms(dto.getGrades().getMidterms());
        grades.setFinal_ex(dto.getGrades().getFinal_ex());


        gradesRepository.save(grades);
        studentRepository.save(student1);
        courseRepository.save(course);
    }


    public void courseRegistrstion(CourseRegistrationDTO dto) {
        Student student1 = studentRepository.findById(dto.getStudId()).orElseThrow(() ->
                new IllegalStateException("student with id: " + dto.getStudId() + " does not exist"));
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow(() ->
                new IllegalStateException("course with id: " + dto.getCourseId() + " does not exist"));
//        Set<Course> courseSet = courseRepository.findAllById(dto.getCourseId());
//        Set<Student> studentSet = studentRepository.findAllById(dto.getStudId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(course.getRegTime().toInstant(), ZoneId.systemDefault());

//        if (now.isEqual(localDateTime) || now.isBefore(localDateTime)) {
//            throw new IllegalStateException("You cannot register course before the registration time!");
//        }
        student1.getCourses().add(course);
        course.getStudents().add(student1);
//        student1.setCourses(courseSet);
//        course.setStudents(studentSet);
        studentRepository.save(student1);
        courseRepository.save(course);
    }
}
