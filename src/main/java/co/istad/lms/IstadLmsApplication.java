package co.istad.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IstadLmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IstadLmsApplication.class, args);
    }

}
