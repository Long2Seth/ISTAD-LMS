package co.istad.lms.features.gittrack;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramService {

    private final String botToken = "7323526195:AAEECJgPs9xTwZKbmZY5c4eKIy7hBOg3us0";
    private final String chatId = "824665384";

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendGitPushInfo(String projectName, String author, String message, String branch, String dateTime) {
        String telegramMessage = String.format("""
            Project: %s
            Author: %s
            Message: %s
            Branch: %s
            DateTime: %s
            """, projectName, author, message, branch, dateTime);

        String url = String.format("https://api.telegram.org/bot%s/sendMessage", botToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = String.format("{\"chat_id\":\"%s\",\"text\":\"%s\"}", chatId, telegramMessage);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to send message to Telegram");
        }
    }
}
