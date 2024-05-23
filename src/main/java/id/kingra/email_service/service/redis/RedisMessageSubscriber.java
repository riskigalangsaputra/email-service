package id.kingra.email_service.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.kingra.email_service.dto.EmailDto;
import id.kingra.email_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageListener {

    private final EmailService emailService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = message.toString();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EmailDto emailDto = objectMapper.readValue(msg, EmailDto.class);
            emailService.sendingEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
