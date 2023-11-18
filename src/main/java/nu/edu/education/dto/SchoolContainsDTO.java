package nu.edu.education.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SchoolContainsDTO {
    private Long schoolId;
    private Long studId;
    private Long courseId;
}
