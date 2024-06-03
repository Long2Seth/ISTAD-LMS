package co.istad.lms.features.score;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.score.dto.ScoreDetailResponse;
import co.istad.lms.features.score.dto.ScoreRequest;
import co.istad.lms.features.score.dto.ScoreUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('assessment:write')")
    void creatScore(@Valid @RequestBody ScoreRequest scoreRequest) {

        scoreService.createScore(scoreRequest);

    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('assessment:read')")
    ScoreDetailResponse getScoreByUuid(@PathVariable String uuid) {

        return scoreService.getScoreByUuid(uuid);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('assessment:read')")
    public Page<ScoreDetailResponse> getAllScores(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return scoreService.getAllScores(pageNumber, pageSize);
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('assessment:update')")
    public ScoreDetailResponse updateScore(@PathVariable String uuid,
                                           @Valid @RequestBody ScoreUpdateRequest scoreUpdateRequest) {

        return scoreService.updateScoreByUuid(uuid, scoreUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('assessment:delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore(@PathVariable String uuid) {

        scoreService.deleteScoreByUuid(uuid);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('assessment:read')")
    public Page<ScoreDetailResponse> filterScores(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return scoreService.filterScores(filterDto, pageNumber, pageSize);
    }
}
