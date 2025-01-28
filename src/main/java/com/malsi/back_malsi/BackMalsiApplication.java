package com.malsi.back_malsi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackMalsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackMalsiApplication.class, args);
	}

	@Bean
	CommandLineRunner start() {
		return args -> {
			System.out.println("Hello World");
		};
	}
}
