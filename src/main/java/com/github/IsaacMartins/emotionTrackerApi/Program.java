package com.github.IsaacMartins.emotionTrackerApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Program {

	public static void main(String[] args) {
		SpringApplication.run(Program.class, args);
	}
}
