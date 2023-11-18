package nu.edu.education.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseRegistrationDTO {
    private Long studId;
    private Long courseId;
}
