package com.reymar.lesson4;

import com.reymar.lesson4.model.Laptop;
import com.reymar.lesson4.repository.LaptopRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lesson4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson4Application.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(LaptopRepository laptopRepository) {
		return args -> {
			laptopRepository.save(new Laptop("DELL", "inspiron", "omar"));
			laptopRepository.save(new Laptop("ASUS", "zen", "reinaldo"));
		};
	}

}
