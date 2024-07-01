package ru.itpark.userservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate template) {
        return args -> {
            template.send("test",  "hello world");
            System.out.println("Hello World!");
        };
    }

    @KafkaListener(topics = {"test"}, groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload ConsumerRecord<String, String> message) {
        System.out.println("Received: " + message.value());
    }

}
