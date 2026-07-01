package com.aniket.impactfund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImpactfundBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImpactfundBackendApplication.class, args);
	}

}
