package nu.edu.education.controller;

import lombok.RequiredArgsConstructor;
import nu.edu.education.dto.SchoolContainsDTO;
import nu.edu.education.entities.School;
import nu.edu.education.services.impl.SchoolServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {
    private final SchoolServiceImpl schoolServiceImpl;

    @PostMapping
    public School saveNewSchool(@RequestBody School school) {

        return schoolServiceImpl.addNewSchool(school);
    }

    @GetMapping("/findall")
    public List<School> getAll() {
        return schoolServiceImpl.findAllSchool();
    }

    @GetMapping("/find/{schoolid}")
    public Optional<School> getbyId(@PathVariable("scoolid") Long schoolid) {
        return schoolServiceImpl.findScoolById(schoolid);
    }

    @DeleteMapping("/delete/{schoolId}")
    public void delSchool(@PathVariable("schoolId") Long schoolId) {
        schoolServiceImpl.deleteSchool(schoolId);
    }

    @PutMapping("/parts")
    public void schoolParts(@RequestBody SchoolContainsDTO dto) {
        schoolServiceImpl.addToSchool(dto);
    }

    @PutMapping("/update/{schoolId}")
    public void updateSchood(@PathVariable("schoolId") Long schoolId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String abbreviation,
                             @RequestParam(required = false) String major_name,
                             @RequestParam(required = false) Integer yearOf_studying){
        schoolServiceImpl.updateA_School(schoolId, name, abbreviation,
                major_name, yearOf_studying);

    }
}
