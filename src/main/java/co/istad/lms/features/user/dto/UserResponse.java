package co.istad.lms.features.user.dto;



import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;


@Builder
public record UserResponse(



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




        Set<String> skills


        ){
}
