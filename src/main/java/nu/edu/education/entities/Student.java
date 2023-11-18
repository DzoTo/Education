package nu.edu.education.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Student_Table")
@Getter
@Setter
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;


    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private Set<Course> courses;

//    @OneToMany(targetEntity = Grades.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "stud_grades", referencedColumnName = "id")
//    private List<Grades> stud_grade;

    @OneToMany(targetEntity = Grades.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private List<Grades> stud_grade;
}
