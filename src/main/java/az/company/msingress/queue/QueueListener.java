package az.company.msingress.queue;

import az.company.msingress.model.request.CreateCardRequest;
import az.company.msingress.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class QueueListener {
    private final ObjectMapper objectMapper;
    private final CardService cardService;

    @SneakyThrows
    @RabbitListener(queues = "${rabbitmq.queue.card}")
    public void receiveMessage(String message) {
        log.info("ActionLog.receiveMessage.start message: {}", message);
        var dto = objectMapper.readValue(message, CreateCardRequest.class);
        cardService.saveCard(dto);
    }
}
