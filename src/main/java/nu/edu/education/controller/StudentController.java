package nu.edu.education.controller;

import lombok.RequiredArgsConstructor;
import nu.edu.education.dto.CourseRegistrationDTO;
import nu.edu.education.dto.StudentMarksDTO;
import nu.edu.education.entities.Student;
import nu.edu.education.services.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;


    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentServiceImpl.addNewStudent(student);
    }

    @GetMapping("/findall")
    public List<Student> findAll() {
        return studentServiceImpl.getAll();
    }

    @GetMapping("/find/{studentid}")
    public Optional<Student> findById(@PathVariable("studentid") Long studentid) {
        return studentServiceImpl.findStudentById(studentid);
    }

    @DeleteMapping(path = "/delete{studentid}")
    public void deleteStudent(@PathVariable("studentid") Long studentid) {
        studentServiceImpl.deleteStudent(studentid);
    }

    @PutMapping("/update{studentid}")
    public void updateStudent(@PathVariable("studentid") Long studentid,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String surname,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String username) {
        studentServiceImpl.updateStudent(studentid, name, surname, email, username);
    }

    @PutMapping("/studtograde")
    public void addGradesToStudent(@RequestBody StudentMarksDTO dto) {
        studentServiceImpl.markGrades(dto);
    }

    @PutMapping("/rega")
    public void regaOfCourses(@RequestBody CourseRegistrationDTO dto) {
        studentServiceImpl.courseRegistrstion(dto);
    }
}
