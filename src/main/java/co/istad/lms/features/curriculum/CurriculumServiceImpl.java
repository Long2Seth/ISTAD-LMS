package co.istad.lms.features.curriculum;


import co.istad.lms.domain.Curriculum;
import co.istad.lms.domain.json.SubjectOfSemester;
import co.istad.lms.features.curriculum.dto.CurriculumRequest;
import co.istad.lms.features.curriculum.dto.CurriculumResponse;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterRequest;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterResponse;
import co.istad.lms.mapper.CurriculumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final CurriculumMapper curriculumMapper;


    @Override
    public Page<CurriculumResponse> getAllCurriculums(int page, int limit) {

        // Get all curriculums from database
        PageRequest pageRequest = PageRequest.of(page, limit);

        Page<Curriculum> curriculums = curriculumRepository.findAll(pageRequest);

        return curriculums.map(curriculumMapper::toCurriculumResponse);


    }

    @Override
    public CurriculumResponse createCurriculum(CurriculumRequest curriculumRequest) {

        if (curriculumRepository.existsBySemesterAndYear(curriculumRequest.semester(), curriculumRequest.year())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Curriculum with year = %s and semester = %s already exists", curriculumRequest.year(), curriculumRequest.semester())
            );
        }

        Curriculum curriculum = curriculumMapper.toCurriculum(curriculumRequest);
        //save curriculum to database
        curriculumRepository.save(curriculum);

        return curriculumMapper.toCurriculumResponse(curriculum);

    }

    @Override
    public SubjectOfSemesterResponse addSubjectToCurriculum(SubjectOfSemesterRequest subjectOfSemesterRequest, String year, String semester) {
        Curriculum curriculum = curriculumRepository.findBySemesterAndYear(semester, year)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Curriculum with year = %s and semester = %s not found", year, semester)
                ));

        // Merge existing subjects with new subjects
        List<SubjectOfSemester> existingSubjects = curriculum.getSubjectsOfSemester();
        existingSubjects.addAll(subjectOfSemesterRequest.subjectsOfSemester());

        // Set merged subjects to the curriculum
        curriculum.setSubjectsOfSemester(existingSubjects);

        // Save curriculum to database
        curriculumRepository.save(curriculum);

        return curriculumMapper.toSubjectOfSemesterResponse(curriculum);
    }

    @Override
    public CurriculumResponse getCurriculumByYearAndSemester(String year, String semester) {

        Curriculum curriculum = curriculumRepository.findBySemesterAndYear(semester, year)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Curriculum with year = %s and semester = %s not found", year, semester)
                        )
                );

        return curriculumMapper.toCurriculumResponse(curriculum);

    }

    @Override
    public CurriculumResponse updateCurriculumBySemesterAndYear(CurriculumRequest curriculumRequest, String year, String semester) {
        Curriculum curriculum = curriculumRepository.findBySemesterAndYear(semester, year)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Curriculum with year = %s and semester = %s not found", year, semester)
                        )
                );

        curriculum.setYear(curriculumRequest.year());
        curriculum.setSemester(curriculumRequest.semester());
        curriculum.setSubjectsOfSemester(curriculumRequest.subjectsOfSemester());

        //save curriculum to database
        curriculumRepository.save(curriculum);

        return curriculumMapper.toCurriculumResponse(curriculum);

    }

    @Override
    public void deleteCurriculumBySemesterAndYear(String year, String semester) {

        Curriculum curriculum = curriculumRepository.findBySemesterAndYear(semester, year)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Curriculum with year = %s and semester = %s not found", year, semester)
                        )
                );

        curriculumRepository.delete(curriculum);

    }
}
