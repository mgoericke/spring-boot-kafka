package de.javamark.kafka.services;

import de.javamark.kafka.model.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Profile("producer")
@Service
@Slf4j
@RequiredArgsConstructor
public class DomainEventProducer {

    static final String TOPIC = "domainEvents";

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    public CompletableFuture<SendResult<String, DomainEvent>> sendMessage(final DomainEvent event) {
        log.info("[#] send message: {}", event.getBody());
        final Message<DomainEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        return this.kafkaTemplate.send(message).completable();
    }
}
