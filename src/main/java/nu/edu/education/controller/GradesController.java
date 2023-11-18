package nu.edu.education.controller;

import lombok.RequiredArgsConstructor;
import nu.edu.education.entities.Grades;
import nu.edu.education.services.impl.GradesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradesController {
    private final GradesServiceImpl gradesServiceImpl;

    @PostMapping
    public Grades addGrade(@RequestBody Grades grades) {
        return gradesServiceImpl.saveGrades(grades);
    }

    @GetMapping("/findall")
    public List<Grades> findAll() {
        return gradesServiceImpl.getAll();
    }

    @GetMapping("/find/{gradeid}")
    public Optional<Grades> findById(@PathVariable("gradeid") Long gradeid) {
        return gradesServiceImpl.findGradesById(gradeid);
    }

    @DeleteMapping("/del/{gradeId}")
    public void deleteMark(@PathVariable("gradeId") Long gradeId) {
        gradesServiceImpl.deleteGrades(gradeId);
    }

    @PutMapping("/updategrades{gradeid}")
    public void updateGrades(@PathVariable("gradeid") Long gradeid,
                             @RequestParam(required = false) Double quizzes,
                             @RequestParam(required = false) Double attendance,
                             @RequestParam(required = false) Double midterms,
                             @RequestParam(required = false) Double final_ex) {
        gradesServiceImpl.updateGrades(gradeid, quizzes, attendance, midterms, final_ex);
    }
}