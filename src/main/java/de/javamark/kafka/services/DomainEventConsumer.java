package de.javamark.kafka.services;

import de.javamark.kafka.model.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Profile("consumer")
@Service
@Slf4j
public class DomainEventConsumer {

    @KafkaListener(topics = {DomainEventProducer.TOPIC}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(final @Payload DomainEvent poJo, final @Headers MessageHeaders headers) {
        log.info("[+] receive message: {}", poJo.getBody());
    }
}
