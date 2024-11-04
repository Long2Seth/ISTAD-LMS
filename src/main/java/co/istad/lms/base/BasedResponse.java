package co.istad.lms.base;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class BasedResponse<T> {
    private T payload;
}
