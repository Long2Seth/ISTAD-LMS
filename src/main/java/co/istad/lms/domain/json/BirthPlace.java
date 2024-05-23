package co.istad.lms.domain.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BirthPlace {

    private String cityOrProvince;
    private String khanOrDistrict;
    private String sangkatOrCommune;
    private String villageOrPhum;
    private String street;
    private String houseNumber;
}
