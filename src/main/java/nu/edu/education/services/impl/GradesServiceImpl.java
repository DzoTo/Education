package nu.edu.education.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nu.edu.education.entities.Grades;
import nu.edu.education.repositories.GradesRepository;
import nu.edu.education.services.interfaces.GradesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradesServiceImpl implements GradesService {
    private final GradesRepository gradesRepository;

//    public GradeService(GradesRepository gradesRepository) {
//        this.gradesRepository = gradesRepository;
//    }

    public List<Grades> getAll() {
        return gradesRepository.findAll();
    }

    public Optional<Grades> findGradesById(Long gradeid) {
        boolean exists = gradesRepository.existsById(gradeid);
        if(!exists){
            throw new IllegalStateException("student with id: " + gradeid + " does not exist");
        }
        return gradesRepository.findById(gradeid);
    }

    public Grades saveGrades(Grades grades) {
        grades.setFinal_grade(calculateFinalGrade(grades));
        return gradesRepository.save(grades);
    }

    public Double calculateFinalGrade(Grades grades) {
        if (grades.getQuizzes() != null && grades.getAttendance() != null &&
                grades.getMidterms() != null && grades.getFinal_ex() != null) {
            return grades.getQuizzes() * 0.15 + grades.getAttendance() * 0.05 + grades.getMidterms() * 0.5 + grades.getFinal_ex() * 0.3;
        }
        return 0.0000;
    }


    public void deleteGrades(Long id){
        boolean exists = gradesRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("grade with id: " + id + "doesn't exist");
        }
        gradesRepository.deleteById(id);
    }

    @Transactional
    public void updateGrades(Long gradeid, Double quizzes, Double attendance, Double midterms, Double final_ex){
        Grades  grades = gradesRepository.findById(gradeid).orElseThrow(() ->
                new IllegalStateException("grade with id: " + gradeid + " does not exist"));

        if(quizzes!=null && !Objects.equals(grades.getQuizzes(), quizzes)){
            grades.setQuizzes(quizzes);
        }

        if(attendance!=null && !Objects.equals(grades.getAttendance(), attendance)){
            grades.setAttendance(attendance);
        }

        if(midterms!=null && !Objects.equals(grades.getMidterms(), midterms)){
            grades.setMidterms(midterms);
        }

        if(final_ex!=null && !Objects.equals(grades.getFinal_ex(), final_ex)){
            grades.setFinal_ex(final_ex);
        }

        grades.setFinal_grade(calculateFinalGrade(grades));

    }

}
