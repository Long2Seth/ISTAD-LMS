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
public class BasedResponse <T> {

    private T payload;
    private String message;
    private Object metadata; // relates to pagination
    private int status;

    public static <T> BasedResponse<T> createSuccess() {
        return new BasedResponse<T>()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Created Successfully!!! ")
                ;
    }

    public static <T> BasedResponse<T> ok() {
        return new BasedResponse<T>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Successfully Retrieved the data !!! ")
                ;
    }


    public static <T> BasedResponse<T> notFound(){
        return new BasedResponse<T>()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage("Items could not be found!! ");
    }


    public static <T> BasedResponse<T> badRequest(){
        return new BasedResponse<T>()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage("Bad request provided !");
    }


    public static <T> BasedResponse<T> updateSuccess(){
        return new BasedResponse<T>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Successfully update the entry!");
    }


}
