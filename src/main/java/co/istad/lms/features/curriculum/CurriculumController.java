package co.istad.lms.features.curriculum;


import co.istad.lms.features.curriculum.dto.CurriculumRequest;
import co.istad.lms.features.curriculum.dto.CurriculumResponse;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterRequest;
import co.istad.lms.features.curriculum.dto.SubjectOfSemesterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/curriculums")
public class CurriculumController {


    private final CurriculumService curriculumService;



    @GetMapping
    public Page<CurriculumResponse> getAllCurriculums(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit) {
        return curriculumService.getAllCurriculums(page, limit);
    }




    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CurriculumResponse createCurriculum(@Valid @RequestBody CurriculumRequest curriculumRequest) {
        return curriculumService.createCurriculum(curriculumRequest);
    }





    @GetMapping({"/{year}/{semester}"})
    public CurriculumResponse getCurriculumByYearAndSemester(@PathVariable String year, @PathVariable String semester) {
        return curriculumService.getCurriculumByYearAndSemester(year, semester);
    }




    @PutMapping({"/{year}/{semester}"})
    public CurriculumResponse updateCurriculumBySemesterAndYear(@Valid @RequestBody CurriculumRequest curriculumRequest, @PathVariable String year, @PathVariable String semester) {
        return curriculumService.updateCurriculumBySemesterAndYear(curriculumRequest, year, semester);
    }




    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/{year}/{semester}"})
    public void deleteCurriculumBySemesterAndYear(@PathVariable String year, @PathVariable String semester) {
        curriculumService.deleteCurriculumBySemesterAndYear(year, semester);
    }




    @PatchMapping("/{year}/{semester}")
    public SubjectOfSemesterResponse addSubjectToCurriculum(@RequestBody SubjectOfSemesterRequest subjectOfSemesterRequest,
                                                            @PathVariable String year,
                                                            @PathVariable String semester) {
        return curriculumService.addSubjectToCurriculum(subjectOfSemesterRequest, year, semester);
    }





}
