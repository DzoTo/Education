package nu.edu.education.services.interfaces;

import nu.edu.education.entities.Grades;

import java.util.List;
import java.util.Optional;

public interface GradesService {

    public List<Grades> getAll();

    public Optional<Grades> findGradesById(Long gradeid);
    public Grades saveGrades(Grades grades);
    public Double calculateFinalGrade(Grades grades);
    public void updateGrades(Long gradeid, Double quizzes, Double attendance, Double midterms, Double final_ex);

}
