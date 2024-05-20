package co.istad.lms.features.shift;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ShiftController {
    private final ShiftService shiftService;
}
