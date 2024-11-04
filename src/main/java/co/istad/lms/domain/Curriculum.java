package co.istad.lms.domain;


import co.istad.lms.domain.json.SubjectOfSemester;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String year; // foundation year , second year , third year , forth year

    private String semester; // first semester , second semester

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<SubjectOfSemester> subjectsOfSemester;

}
