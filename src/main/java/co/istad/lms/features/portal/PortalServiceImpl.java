package co.istad.lms.features.portal;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Shift;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.degree.DegreeRepository;
import co.istad.lms.features.portal.dto.PortalDegreeResponse;
import co.istad.lms.features.portal.dto.PortalShiftResponse;
import co.istad.lms.features.portal.dto.PortalStudyProgramResponse;
import co.istad.lms.features.shift.ShiftRepository;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.mapper.PortalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortalServiceImpl implements  PortalService {

    private final StudyProgramRepository studyProgramRepository;

    private final DegreeRepository degreeRepository;

    private final ShiftRepository shiftRepository;

    private final PortalMapper portalMapper;
    @Override
    public Set<PortalStudyProgramResponse> getAllStudyPrograms() {

        Set<StudyProgram> studyPrograms =studyProgramRepository.findAllByIsDeletedFalseAndIsDraftFalse();

        System.out.println("studyPrograms size = "+studyPrograms.size());
        return null;
//        return studyPrograms.stream().map(portalMapper::toPortalStudyProgramResponse).collect(Collectors.toSet());
    }

    @Override
    public Set<PortalDegreeResponse> getAllDegrees() {

        Set<Degree> degrees =degreeRepository.findAllByIsDeletedFalseAndIsDraftFalse();

        return degrees.stream().map(portalMapper::toPortalDegreeResponse).collect(Collectors.toSet());

    }

    @Override
    public Set<PortalShiftResponse> getAllShifts() {

        Set<Shift> shifts=shiftRepository.findAllByIsDeletedFalseAndIsDraftFalse();

        return shifts.stream().map(portalMapper::toPortalShiftResponse).collect(Collectors.toSet());
    }
}