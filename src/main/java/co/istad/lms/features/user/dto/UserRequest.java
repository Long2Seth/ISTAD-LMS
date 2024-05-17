package co.istad.lms.features.user.dto;

public record UserRequest
        (
                String name_en,
                String name_kh,
                String email,
                String userName,
                String password,
                String profileImage,
                String phoneNumber,
                String cityOrProvince,
                String khanOrDistrict,
                String sangkatOrCommune,
                String street,
                Long roleId
        ){
}
