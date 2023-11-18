package nu.edu.education.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nu.edu.education.entities.Grades;
import nu.edu.education.entities.Student;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class StudentMarksDTO {
    private Long studId;
    private Long courseId;
    private Grades grades;

}
