package nu.edu.education.controller;

import lombok.RequiredArgsConstructor;
import nu.edu.education.entities.Course;
import nu.edu.education.services.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseServiceImpl;

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseServiceImpl.saveCourse(course);
    }

    @GetMapping("/findall")
    public List<Course> getAllCourses() {
        return courseServiceImpl.findAllCourses();
    }

    @GetMapping("/find/{courseid}")
    public Optional<Course> getCourseById(@PathVariable("courseid") Long courseid) {
        return courseServiceImpl.getCourseById(courseid);
    }

    @DeleteMapping("/delete/{courseId}")
    public void deleteCourses(@PathVariable("courseId") Long courseId) {
        courseServiceImpl.deleteA_Course(courseId);
    }

    @PutMapping("/update/{courseId}")
    public void updateCourse(@PathVariable("courseId") Long courseId,
                             @RequestParam(required = false) String course_name,
                             @RequestParam(required = false) String abbreviation,
                             @RequestParam(required = false) int capacity,
                             @RequestParam(required = false) int weight_credit,
                             @RequestParam(required = false) Date regTime) {
        courseServiceImpl.updateA_Course(courseId, course_name, abbreviation,
                capacity, weight_credit, regTime);

    }
}
