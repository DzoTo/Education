package nu.edu.education.services.interfaces;

import nu.edu.education.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public Course saveCourse(Course course);
    public List<Course> findAllCourses();
    public Optional<Course> getCourseById(Long id);

}
