package com.example.parkinglot;

import com.example.parkinglot.controllers.InitializationController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class ParkinglotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkinglotApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeParkingLot(InitializationController initializationController) {
		return args -> {
			ResponseEntity<?> response = initializationController.initialize(
					"MainParkingLot", 3, 2, 10
			);
			System.out.println("Initialization Response: " + response.getBody());
		};
	}
}
