package co.istad.lms.features.lecture;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Course;
import co.istad.lms.domain.Lecture;
import co.istad.lms.features.course.CourseRepository;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureRequest;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.lecture.dto.LectureUpdateRequest;
import co.istad.lms.mapper.LectureMapper;
import co.istad.lms.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureMapper lectureMapper;

    private final LectureRepository lectureRepository;

    private final CourseRepository courseRepository;

    private final BaseSpecification<Lecture> baseSpecification;

    @Override
    public void createLecture(LectureRequest lectureRequest) {


        //check course from DTO by uuid
        Course course =
                courseRepository.findByUuid(lectureRequest.courseUuid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course = %s has not been found", lectureRequest.courseUuid())));

        // map DTO to entity
        Lecture lecture = lectureMapper.fromLectureRequest(lectureRequest);

        LocalTime startTime = DateTimeUtil.stringToLocalTime(lectureRequest.startTime(), "startTime");

        LocalTime endTime = DateTimeUtil.stringToLocalTime(lectureRequest.endTime(), "endTime");

        if (endTime.isBefore(startTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endTime must be after startTime");
        }

        LocalDate lectureDate = DateTimeUtil.stringToLocalDate(lectureRequest.lectureDate(), "lectureDate");

        //set isDeleted to false(enable)
        lecture.setIsDeleted(false);

        //set startTime
        lecture.setStartTime(startTime);

        //set endTime
        lecture.setEndTime(endTime);

        //set lectureDate
        lecture.setLectureDate(lectureDate);

        //set uuid to lecture
        lecture.setUuid(UUID.randomUUID().toString());

        //set course to lecture
        lecture.setCourse(course);

        //save to database
        lectureRepository.save(lecture);

    }

    @Override
    public LectureDetailResponse getLectureByUuid(String uuid) {

        //find lecture by uuid
        Lecture lecture =
                lectureRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found.", uuid)));

        //return lecture detail
        return lectureMapper.toLectureDetailResponse(lecture);
    }


    @Override
    public Page<LectureDetailResponse> getAllLectures(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.ASC, "lectureDate");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all lecture in database
        Page<Lecture> lectures = lectureRepository.findAll(pageRequest);

        //map entity to DTO and return
        return lectures.map(lectureMapper::toLectureDetailResponse);
    }


    @Override
    public LectureDetailResponse updateLectureByUuid(String uuid, LectureUpdateRequest lectureUpdateRequest) {

        //find lecture by uuid
        Lecture lecture =
                lectureRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found.", uuid)));

        //map DTO to entity
        lectureMapper.updateLectureFromRequest(lecture, lectureUpdateRequest);

        //check startTime null or empty
        if (lectureUpdateRequest.startTime() != null && !lectureUpdateRequest.startTime().trim().isEmpty()) {
            LocalTime startTime = DateTimeUtil.stringToLocalTime(lectureUpdateRequest.startTime(), "startTime");
            lecture.setStartTime(startTime);
        }

        if (lectureUpdateRequest.endTime() != null && !lectureUpdateRequest.endTime().trim().isEmpty()) {
            LocalTime endTime = DateTimeUtil.stringToLocalTime(lectureUpdateRequest.endTime(), "endTime");
            lecture.setEndTime(endTime);
        }

        //validate end time must be after start time
        if (lecture.getEndTime().isBefore(lecture.getStartTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endTime must be after startTime");
        }

        //check lectureDate from DTO null or not
        if (lectureUpdateRequest.lectureDate() != null && !lectureUpdateRequest.lectureDate().trim().isEmpty()) {

            //update lectureDate from DTO
            LocalDate lectureDate = DateTimeUtil.stringToLocalDate(lectureUpdateRequest.lectureDate(), "lectureDate");
            lecture.setLectureDate(lectureDate);
        }

        //save to database
        lectureRepository.save(lecture);

        //return Lecture response
        return lectureMapper.toLectureDetailResponse(lecture);
    }


    @Override
    public void deleteLectureByUuid(String uuid) {

        //find lecture in database by uuid
        Lecture lecture =
                lectureRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found.", uuid)));

        //delete lecture in database
        lectureRepository.delete(lecture);
    }

    @Override
    public void enableLectureByUuid(String uuid) {

        //validate lecture from dto by uuid
        Lecture lecture =
                lectureRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found ! ", uuid)));

        //set isDeleted to false(enable)
        lecture.setIsDeleted(false);

        //save to database
        lectureRepository.save(lecture);
    }

    @Override
    public void disableLectureByUuid(String uuid) {

        //validate lecture from dto by uuid
        Lecture lecture =
                lectureRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found ! ", uuid)));

        //set isDeleted to true(disable)
        lecture.setIsDeleted(true);

        //save to database
        lectureRepository.save(lecture);

    }

    @Override
    public Page<LectureDetailResponse> filterLectures(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.ASC, "lectureDate");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Lecture entities based on the criteria provided
        Specification<Lecture> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Lecture> lectures = lectureRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return lectures.map(lectureMapper::toLectureDetailResponse);

    }
}
