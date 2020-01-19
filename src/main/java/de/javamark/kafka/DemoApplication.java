package de.javamark.kafka;

import de.javamark.kafka.model.DomainEvent;
import de.javamark.kafka.services.DomainEventProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    @Profile("producer-2")
    CommandLineRunner runner(final DomainEventProducer domainEventProducer) {
        return args -> {
            while (true) {
                final String body = "current timestamp " + LocalDateTime.now();
                final DomainEvent poJo = new DomainEvent();
                poJo.setId(System.currentTimeMillis());
                poJo.setBody(body);
                domainEventProducer.sendMessage(poJo);

                Thread.sleep(10123);
            }
        };
    }
}
