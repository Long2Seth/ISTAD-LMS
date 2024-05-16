package co.istad.istadlms.features;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class TestDeploy {
    @GetMapping("")
    public String getString(){
        return "hello";
    }
}
