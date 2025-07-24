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
		// Запускаем контекст
		var ctx = SpringApplication.run(DemoApplication.class, args);

		// Берём бином Example (в котором уже есть инжектед PaymentProcessor)
		Example example = ctx.getBean(Example.class);
		example.go();
	}
}
