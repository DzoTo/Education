package nu.edu.education.services.impl;

import lombok.RequiredArgsConstructor;
import nu.edu.education.entities.Course;
import nu.edu.education.entities.Student;
import nu.edu.education.repositories.CourseRepository;
import nu.edu.education.services.interfaces.CourseService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        boolean exists = courseRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("student with id: " + id + " does not exist");
        }
        return courseRepository.findById(id);
    }

    public void deleteA_Course(Long id){
        if(!courseRepository.existsById(id)){
            throw new IllegalStateException("Course with id: " + id + "doesn't exist");
        }
        courseRepository.deleteById(id);
    }

    public void updateA_Course(Long id, String course_name, String abbreviation, Integer capacity, Integer weight_credit, Date regTime){
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Course with id: " + id + " does not exist"));

        if(course_name!=null && course_name.length()>0 && !Objects.equals(course.getCourse_name(), course_name)){
            course.setCourse_name(course_name);
        }

        if(abbreviation!=null && abbreviation.length()>0 && !Objects.equals(course.getAbbreviation(), abbreviation)){
            course.setAbbreviation(abbreviation);
        }
        Integer num = null;
        if(capacity != num && !Objects.equals(course.getCapacity(), capacity)){
            course.setCapacity(capacity);
        }

        if(weight_credit!=num && !Objects.equals(course.getWeight_credit(), weight_credit)){
            course.setWeight_credit(weight_credit);
        }

        if(regTime!=null && !Objects.equals(course.getRegTime(), regTime)){
            course.setRegTime(regTime);
        }
    }

}
