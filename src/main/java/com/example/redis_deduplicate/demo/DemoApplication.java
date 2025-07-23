package com.example.redis_deduplicate.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.redis_deduplicate.demo.mapper") // дублирую, но без явного указания - не видит и не добавляет этот бин в контекст

@ComponentScan("com.example.redis_deduplicate.demo")
@EnableJpaRepositories("com.example.redis_deduplicate.demo.repository")
@EntityScan("com.example.redis_deduplicate.demo.model.entity")

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
