package co.istad.lms.features.telegrambot;


import co.istad.lms.features.admission.dto.AdmissionResponse;

public interface TelegramBotService {
    public void sendAdmissionResponse(String messageText);
}
