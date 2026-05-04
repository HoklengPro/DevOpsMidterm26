package com.midterm.devops.task3;

import com.midterm.devops.task3.domain.DemoMessage;
import com.midterm.devops.task3.repo.DemoMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig {

    private final DemoMessageRepository messages;

    @Bean
    CommandLineRunner seedIfEmpty() {
        return args -> {
            if (messages.count() == 0) {
                messages.save(DemoMessage.builder().text("Hello from Task 3 (PostgreSQL via JPA).").build());
                log.info("Inserted seed row into demo_messages.");
            }
        };
    }
}
