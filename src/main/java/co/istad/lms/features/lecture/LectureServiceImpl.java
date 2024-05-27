package co.istad.lms.features.lecture;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Lecture;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureRequest;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.lecture.dto.LectureUpdateRequest;
import co.istad.lms.mapper.LectureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureMapper lectureMapper;
    private final LectureRepository lectureRepository;
    private final BaseSpecification<Lecture> baseSpecification;

    @Override
    public void createLecture(LectureRequest lectureRequest) {

        //validate lecture by alias
        if (lectureRepository.existsByAlias(lectureRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Lecture = %s already exists.", lectureRequest.alias()));
        }

        // map DTO to entity
        Lecture lecture = lectureMapper.fromLectureRequest(lectureRequest);

        //set isDeleted to false(enable)
        lecture.setIsDeleted(false);

        //save to database
        lectureRepository.save(lecture);

    }

    @Override
    public LectureDetailResponse getLectureByAlias(String alias) {

        //find lecture by alias
        Lecture lecture = lectureRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found.", alias)));

        //return lecture detail
        return lectureMapper.toLectureDetailResponse(lecture);
    }


    @Override
    public Page<LectureDetailResponse> getAllLectures(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all lecture in database
        Page<Lecture> lectures = lectureRepository.findAll(pageRequest);

        //map entity to DTO and return
        return lectures.map(lectureMapper::toLectureDetailResponse);
    }


    @Override
    public LectureResponse updateLectureByAlias(String alias, LectureUpdateRequest lectureUpdateRequest) {

        //find lecture by alias
        Lecture lecture = lectureRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found.", alias)));

        //check null alias from DTO
        if (lectureUpdateRequest.alias() != null) {

            //validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(lectureUpdateRequest.alias())) {

                //validate new alias is conflict with other alias or not
                if (lectureRepository.existsByAlias(lectureUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            String.format("Lecture = %s already exist.", lectureUpdateRequest.alias()));
                }
            }
        }

        //map DTO to entity
        lectureMapper.updateLectureFromRequest(lecture, lectureUpdateRequest);

        //save to database
        lectureRepository.save(lecture);

        //return Lecture response
        return lectureMapper.toLectureResponse(lecture);
    }


    @Override
    public void deleteLectureByAlias(String alias) {

        //find lecture in database by alias
        Lecture lecture = lectureRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found.", alias)));

        //delete lecture in database
        lectureRepository.delete(lecture);
    }

    @Override
    public void enableLectureByAlias(String alias) {

        //validate lecture from dto by alias
        Lecture lecture = lectureRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        lecture.setIsDeleted(false);

        //save to database
        lectureRepository.save(lecture);
    }

    @Override
    public void disableLectureByAlias(String alias) {

        //validate lecture from dto by alias
        Lecture lecture = lectureRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Lecture = %s has not been found ! ", alias)));

        //set isDeleted to true(disable)
        lecture.setIsDeleted(true);

        //save to database
        lectureRepository.save(lecture);

    }

    @Override
    public Page<LectureDetailResponse> filterLectures(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering Lecture entities based on the criteria provided
        Specification<Lecture> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Lecture> lectures = lectureRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return lectures.map(lectureMapper::toLectureDetailResponse);

    }
}
