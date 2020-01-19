package de.javamark.kafka.controllers;

import de.javamark.kafka.model.DomainEvent;
import de.javamark.kafka.services.DomainEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Profile("producer")
@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final DomainEventProducer domainEventProducer;

    @PostMapping("/publish")
    public ResponseEntity publish(@RequestBody final DomainEvent message) throws ExecutionException, InterruptedException {
        this.domainEventProducer.sendMessage(message)
                .thenRunAsync(() -> log.info("[#] message sent")).get();
        return ResponseEntity.accepted().build();
    }
}

