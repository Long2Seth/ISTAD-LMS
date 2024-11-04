package co.istad.lms.features.telegrambot;


import co.istad.lms.domain.StudentAdmission;

public interface TelegramBotService {
    public void sendAdmissionResponse(StudentAdmission admission);
}
