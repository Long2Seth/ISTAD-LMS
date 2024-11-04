package co.istad.lms.domain.json;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectOfSemester {

    private String subjectName;
    private Double credit;
    private String hours;

}
