package co.istad.lms.features.transcript;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Score;
import co.istad.lms.domain.Transcript;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.CourseRepository;
import co.istad.lms.features.score.ScorerRepository;
import co.istad.lms.features.student.StudentRepository;
import co.istad.lms.features.transcript.dto.TranscriptRequest;
import co.istad.lms.features.transcript.dto.TranscriptResponse;
import co.istad.lms.features.yearofstudy.YearOfStudyRepository;
import co.istad.lms.mapper.TranscriptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TranscriptServiceImpl implements TranscriptService{

    private final TranscriptMapper transcriptMapper;

    private final StudentRepository studentRepository;

    private final TranscriptRepository transcriptRepository;

    private final YearOfStudyRepository yearOfStudyRepository;

    private final CourseRepository courseRepository;

    private final ScorerRepository scorerRepository;

    @Override
    public void createTranscript(TranscriptRequest transcriptRequest) {

        //get student
        Student student=
                studentRepository.findStudentByUserUuid(transcriptRequest.studentUuid()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("student = %s has not been found",transcriptRequest.studentUuid())));

        //get yearOfStudy by uuid
        YearOfStudy yearOfStudy =
                yearOfStudyRepository.findByUuid(transcriptRequest.yearOfStudyUuid()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("yearOfStudent = %s has not been found",transcriptRequest.yearOfStudyUuid())));

//        Set<Score> scores = scorerRepository.finAllBy
        Transcript transcript=transcriptMapper.fromTranscriptRequest(transcriptRequest);

    }

    @Override
    public TranscriptResponse updateTranscript(TranscriptRequest transcriptRequest) {
        return null;
    }

    @Override
    public Page<TranscriptResponse> getAllTranscript(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Page<TranscriptResponse> filterTranscript(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {
        return null;
    }
}
