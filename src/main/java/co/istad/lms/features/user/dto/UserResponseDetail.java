package co.istad.lms.features.user.dto;

import co.istad.lms.features.authority.dto.AuthorityResponseToUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserResponseDetail(


        String uuid,


        String nameEn,


        String nameKh,


        String username,


        String position,


        String email,


        String phoneNumber,


        LocalDate dob,


        String gender,


        String profileImage,



        String linkGit,


        String linkTelegram,


        String linkLinkedIn,


        Set< String> educations,




        Set<String> skills ,


        String currentAddress,



        String birthPlace
) {
}
