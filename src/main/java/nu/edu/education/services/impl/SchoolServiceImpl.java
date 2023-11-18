package nu.edu.education.services.impl;

import lombok.RequiredArgsConstructor;
import nu.edu.education.dto.SchoolContainsDTO;
import nu.edu.education.entities.Course;
import nu.edu.education.entities.School;
import nu.edu.education.entities.Student;
import nu.edu.education.repositories.CourseRepository;
import nu.edu.education.repositories.SchoolRepository;
import nu.edu.education.repositories.StudentRepository;
import nu.edu.education.services.interfaces.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public School addNewSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> findAllSchool() {
        return schoolRepository.findAll();
    }

    public Optional<School> findScoolById(Long schoolId) {
        boolean exists = schoolRepository.existsById(schoolId);
        if (!exists) {
            throw new IllegalStateException("student with id: " + schoolId + " does not exist");
        }
        return schoolRepository.findById(schoolId);
    }

    public void addToSchool(SchoolContainsDTO dto) {
        School school = schoolRepository.findById(dto.getSchoolId()).orElseThrow(() ->
                new IllegalStateException("school with id: " + dto.getSchoolId() + "does not exist"));
        Student student = studentRepository.findById(dto.getStudId()).orElseThrow(() ->
                new IllegalStateException("student with id: " + dto.getStudId() + "does not exist"));
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow(() ->
                new IllegalStateException("course with id: " + dto.getCourseId() + "does not exist"));
        school.setCourseList(courseRepository.findAllById(dto.getCourseId()));
        school.setStudentList(studentRepository.findAllById(dto.getStudId()));
        schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        boolean exists = schoolRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("school with id: " + id + "doesn't exist");
        }

        schoolRepository.deleteById(id);
    }

    public void updateA_School(Long id, String name, String abbreviation ,String major_name, Integer yearOf_studying){
        School school = schoolRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("School with id: " + id + " does not exist"));
        if(name != null && name.length()>0 && !Objects.equals(school.getName(), name)){
            school.setName(name);
        }
        if(abbreviation!=null && abbreviation.length()>0 && !Objects.equals(school.getAbbreviation(), abbreviation)){
            school.setAbbreviation(abbreviation);
        }
        if(major_name!=null && major_name.length()>0 && !Objects.equals(school.getMajor_name(), major_name)){
            school.setMajor_name(major_name);
        }
        Integer num = null;

        if(yearOf_studying!=num && !Objects.equals(school.getYearOf_studying(), yearOf_studying)){
            school.setYearOf_studying(yearOf_studying);
        }

    }


}
