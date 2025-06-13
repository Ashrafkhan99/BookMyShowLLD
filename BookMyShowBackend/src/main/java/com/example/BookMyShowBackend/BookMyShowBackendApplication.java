package com.example.BookMyShowBackend;

import com.example.BookMyShowBackend.Controllers.UserController;
import com.example.BookMyShowBackend.dto.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowBackendApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
		signUpRequestDto.setEmail("ashraf@gmail.com");
		signUpRequestDto.setPassword("ashraf123");

		try {
			userController.signUp(signUpRequestDto);
			System.out.println("User signed up successfully");
		} catch (Exception e) {
			System.out.println("Error during sign up: " + e.getMessage());
		}
	}
}
