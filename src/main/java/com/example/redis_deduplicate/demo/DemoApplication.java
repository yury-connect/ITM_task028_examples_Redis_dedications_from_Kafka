package com.example.redis_deduplicate.demo;

import com.example.redis_deduplicate.demo.model.kafka.MetadataDTO;
import com.example.redis_deduplicate.demo.model.kafka.ParticipantDTO;
import com.example.redis_deduplicate.demo.model.kafka.PaymentDTO;
import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@SpringBootApplication
@ComponentScan("com.example.redis_deduplicate.demo.mapper") // дублирую, но без явного указания - не видит и не добавляет этот бин в контекст

@ComponentScan("com.example.redis_deduplicate.demo")
@EnableJpaRepositories("com.example.redis_deduplicate.demo.repository")
@EntityScan("com.example.redis_deduplicate.demo.model.entity")

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	ParticipantDTO sender = ParticipantDTO.builder()
			.account("12345678901234567890")
			.name("ООО Ромашка")
			.inn("1234567890")
			.build();
	ParticipantDTO receiver = ParticipantDTO.builder()
			.account("12345678901234567890")
			.name("ОДО АяНеРомашка")
			.inn("0001234567890")
			.build();
	PaymentDTO payment = PaymentDTO.builder()
			.id("ПП-12345")
			.docRef("abc123def-4567-89gh-ijkl-mnopqrstuvwx")
			.date(LocalDate.now())
			.amount("10000.50")
			.currency("RUB")
			.sender(sender)
			.recipient(receiver)
			.purpose("Оплата по договору №123 от 2024-01-01")
			.build();
	MetadataDTO metadata = MetadataDTO.builder()
			.eventId(UUID.randomUUID())
			.timestamp(ZonedDateTime.now())
			.build();
	PaymentMessageDTO message = PaymentMessageDTO.builder()
			.metadata(metadata)
			.payment(payment)
			.build();

}
