package nu.edu.education.services.interfaces;

import nu.edu.education.entities.School;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    public School addNewSchool(School school);

    public List<School> findAllSchool();
    public Optional<School> findScoolById(Long schoolId);

}
