package com.kaansenada.cards;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards Service", version = "1.0.0", description = "Cards Service"
		)
)
public class CardsApplication {


	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}